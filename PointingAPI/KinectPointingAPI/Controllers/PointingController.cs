using KinectPointingAPI.Sensor;
using KinectPointingAPI.Utilities;
using Microsoft.Kinect;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading;
using System.Web.Http;
using System.Web.Http.Results;
using System.Windows.Media.Media3D;

namespace KinectPointingAPI.Controllers
{
    [RoutePrefix("api/Pointing")]
    public class PointingController : AnnotationController<Dictionary<string, List<Dictionary<string, double>>>>
    {
        private readonly List<Dictionary<string, double>> _blockConfidences;

        private static readonly int CONNECT_TIMEOUT_MS = 20000;
        private static readonly int POINTING_TIMEOUT_MS = 60000;
        private static readonly string ANNOTATION_TYPE_CLASS = "edu.rosehulman.aixprize.pipeline.types.Pointing";

        public PointingController()
        {
            _blockConfidences = new List<Dictionary<string, double>>();
        }

        public override void ProcessRequest(JToken allAnnotations)
        {
            KinectSensor kinectSensor = SensorHandler.GetSensor();

            int msSlept = 0;
            while (!kinectSensor.IsAvailable)
            {
                Thread.Sleep(500);
                msSlept += 500;
                Debug.WriteLine("Waiting on sensor...");
                if (msSlept >= CONNECT_TIMEOUT_MS)
                {
                    Environment.Exit(-1);
                }
            }

            List<Tuple<JointType, JointType>> bones = new List<Tuple<JointType, JointType>>
            {
                new Tuple<JointType, JointType>(JointType.HandRight, JointType.HandTipRight)
            };
            bool dataReceived = false;
            Body body = null;
            msSlept = 0;
            while (!dataReceived)
            {
                BodyFrame bodyFrame = null;
                Debug.WriteLine("Waiting on body frame...");
                while (bodyFrame == null)
                {
                    bodyFrame = SensorHandler.GetBodyFrame();
                }
                Body[] bodies = new Body[bodyFrame.BodyCount];
                bodyFrame.GetAndRefreshBodyData(bodies);
                Debug.WriteLine("Checking if body is detected in frame...");
                Debug.WriteLine(bodyFrame.BodyCount + " bodies detected");
                int count = 0;
                if (bodyFrame.BodyCount > 0)
                {
                    foreach (Body b in bodies)
                    {
                        if (b.IsTracked)
                        {
                            Debug.WriteLine("Found body frame.");
                            body = b;
                            dataReceived = true;
                            count++;
                        }
                    }
                }

                Debug.WriteLine(count + " bodies tracked");
                Thread.Sleep(100);

                msSlept += 100;
                if (msSlept >= POINTING_TIMEOUT_MS)
                {
                    Environment.Exit(-1);
                }
                bodyFrame.Dispose();
            }

            //// convert the joint points to depth (display) space
            IReadOnlyDictionary<JointType, Joint> joints = body.Joints;
            Dictionary<JointType, CameraSpacePoint> jointPoints = new Dictionary<JointType, CameraSpacePoint>();
            foreach (JointType jointType in joints.Keys)
            {
                // sometimes the depth(Z) of an inferred joint may show as negative
                // clamp down to 0.1f to prevent coordinatemapper from returning (-Infinity, -Infinity)
                CameraSpacePoint position = joints[jointType].Position;
                if (position.Z < 0)
                {
                    position.Z = 0.1f;
                }


                jointPoints[jointType] = position;
            }
            Tuple<JointType, JointType> bone = bones.First();

            List<BlockData> blocks = GetBlocks(allAnnotations);
            ComputeConfidenceScores(bone, jointPoints, blocks);
        }

        public override JsonResult<Dictionary<string, List<Dictionary<string, double>>>> GenerateAnnotationResponse()
        {
            Dictionary<string, List<Dictionary<string, double>>> annotation = new Dictionary<string, List<Dictionary<string, double>>>
            {
                { ANNOTATION_TYPE_CLASS, _blockConfidences }
            };
            return Json(annotation);
        }

        private List<BlockData> GetBlocks(JToken allAnnotations)
        {
            JToken detectedBlocks = allAnnotations["DetectedBlock"];
            if (detectedBlocks == null)
            {
                return new List<BlockData>();
            }

            List<BlockData> allBlocks = new List<BlockData>();
            foreach (JToken blockString in detectedBlocks)
            {
                if (blockString == null) continue;
                int id = blockString["id"].ToObject<int>();
                int centerX = blockString["center_X"].ToObject<int>();
                int centerY = blockString["center_Y"].ToObject<int>();
                double cameraSpaceCenterX = blockString["camera_space_center_X"].ToObject<double>();
                double cameraSpaceCenterY = blockString["camera_space_center_Y"].ToObject<double>();
                double cameraSpaceDepth = blockString["camera_space_depth"].ToObject<double>();
                double rHue = blockString["r_hue"].ToObject<double>();
                double gHue = blockString["g_hue"].ToObject<double>();
                double bHue = blockString["b_hue"].ToObject<double>();

                BlockData block = new BlockData(id, centerX, centerY, rHue, gHue, bHue)
                {
                    cameraSpaceCenterX = cameraSpaceCenterX,
                    cameraSpaceCenterY = cameraSpaceCenterY,
                    cameraSpaceDepth = cameraSpaceDepth
                };
                allBlocks.Add(block);
            }

            return allBlocks;
        }

        private void ComputeConfidenceScores(Tuple<JointType, JointType> bone, Dictionary<JointType, CameraSpacePoint> jointPoints, List<BlockData> blocks)
        {
            JointType handCenterFixture = bone.Item1;
            JointType fingerEndFixture = bone.Item2;
            Vector3D pointingVector = new Vector3D(
               jointPoints[fingerEndFixture].X - jointPoints[handCenterFixture].X,
               jointPoints[fingerEndFixture].Y - jointPoints[handCenterFixture].Y,
               jointPoints[fingerEndFixture].Z - jointPoints[handCenterFixture].Z
            );
            Debug.WriteLine("\n==================================");

            Debug.WriteLine("Hand center point: X=" + jointPoints[handCenterFixture].X + ", Y=" + jointPoints[handCenterFixture].Y + ", Z=" + jointPoints[handCenterFixture].Z);
            Debug.WriteLine("End of finger point: X=" + jointPoints[fingerEndFixture].X + ", Y=" + jointPoints[fingerEndFixture].Y + ", Z=" + jointPoints[fingerEndFixture].Z);
            Debug.WriteLine("Current bone vector: " + pointingVector);

            foreach (BlockData block in blocks)
            {
                Vector3D blockToHandCenter = new Vector3D(
                        block.cameraSpaceCenterX - jointPoints[handCenterFixture].X,
                        block.cameraSpaceCenterY - jointPoints[handCenterFixture].Y,
                        block.cameraSpaceDepth - jointPoints[handCenterFixture].Z
                );
                double confidence = Vector3D.DotProduct(pointingVector, blockToHandCenter) / (pointingVector.Length * blockToHandCenter.Length);
                Debug.WriteLine("Vector to center of hand for block id=" + block.id + " and X=" + block.cameraSpaceCenterX + ", Y=" + block.cameraSpaceCenterY + ", Z=" + block.cameraSpaceDepth + ": " + blockToHandCenter);
                Dictionary<string, double> blockConfidence = new Dictionary<string, double>
                {
                    { "id", block.id },
                    { "confidence", confidence }
                };

                _blockConfidences.Add(blockConfidence);
            }
        }
    }
}
