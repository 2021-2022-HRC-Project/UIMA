using KinectPointingAPI.Image_Processing;
using KinectPointingAPI.Sensor;
using KinectPointingAPI.Utilities;
using Microsoft.Kinect;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Threading;
using System.Web.Http;
using System.Web.Http.Results;
using System.Windows;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using Point = System.Drawing.Point;

namespace KinectPointingAPI.Controllers
{
    /// <summary>
    /// This is the endpoint for object detection using Kinect.
    /// It will return the detected block with x,y,z coordination info, color information in RGB Hue, and assign ID to each block.
    /// </summary>
    [RoutePrefix("api/ObjectDetection")]
    public class ObjectDetectionController : AnnotationController<Dictionary<string, List<Dictionary<string, double>>>>
    {
        private CoordinateMapper _coordinateMapper;
        private FrameDescription _colorFrameDescription;
        private readonly BlockDetector _blockDetector;

        private List<BlockData> _aggregatedData;
        private ColorFrame _currentColorFrame;
        private DepthFrame _currentDepthFrame;

        private const int TimeoutMs = 30000;
        private const string AnnotationTypeClass = "edu.rosehulman.aixprize.pipeline.types.DetectedBlock";

        public ObjectDetectionController()
        {
            _aggregatedData = new List<BlockData>();
            _blockDetector = new BlockDetector();
        }

        public override void ProcessRequest(JToken casJson)
        {
            KinectSensor kinectSensor = SensorHandler.GetSensor();
            _coordinateMapper = kinectSensor.CoordinateMapper;
            _colorFrameDescription = kinectSensor.ColorFrameSource.FrameDescription;

            int timeSlept = 0;
            while (!kinectSensor.IsAvailable)
            {
                Thread.Sleep(5);
                timeSlept += 5;
                if (timeSlept > TimeoutMs)
                {
                    Environment.Exit(-2);
                }
            }

            bool dataReceived = false;
            while (!dataReceived)
            {
                _currentColorFrame = SensorHandler.GetColorFrame();
                if (_currentColorFrame != null)
                {
                    dataReceived = true;
                }
            }

            dataReceived = false;
            while (!dataReceived)
            {
                _currentDepthFrame = SensorHandler.GetDepthFrame();
                if (_currentDepthFrame != null)
                {
                    dataReceived = true;
                }
            }

            _aggregatedData = ProcessBlocksFromFrames();
            _currentColorFrame = null;
            _currentDepthFrame = null;
        }

        public override JsonResult<Dictionary<string, List<Dictionary<string, double>>>> GenerateAnnotationResponse()
        {
            List<Dictionary<string, double>> serializedBlocks = new List<Dictionary<string, double>>();

            foreach (BlockData block in _aggregatedData)
            {
                serializedBlocks.Add(block.ConvertToDict());
            }

            Dictionary<string, List<Dictionary<string, double>>> annotation = new Dictionary<string, List<Dictionary<string, double>>>
            {
                { AnnotationTypeClass, serializedBlocks }
            };
            return Json(annotation);
        }

        private List<BlockData> ProcessBlocksFromFrames()
        {
            Bitmap colorData = ConvertCurrFrameToBitmap();
            List<BlockData> aggregatedData = _blockDetector.DetectBlocks(colorData);
            aggregatedData = ConvertCentersToCameraSpace(aggregatedData);

            return aggregatedData;
        }

        private Bitmap ConvertCurrFrameToBitmap()
        {
            int width = _colorFrameDescription.Width;
            int height = _colorFrameDescription.Height;

            WriteableBitmap pxData = new WriteableBitmap(width, height, 96, 96, PixelFormats.Bgr32, null);
            pxData.Lock();
            _currentColorFrame.CopyConvertedFrameDataToIntPtr(
                pxData.BackBuffer,
                (uint)(width * height * 4),
                ColorImageFormat.Bgra);

            pxData.AddDirtyRect(new Int32Rect(0, 0, width, height));
            pxData.Unlock();
            Bitmap bmp;
            using (MemoryStream outStream = new MemoryStream())
            {
                BitmapEncoder enc = new BmpBitmapEncoder();
                enc.Frames.Add(BitmapFrame.Create(pxData));
                enc.Save(outStream);
                bmp = new Bitmap(outStream);
            }
            return bmp;
        }

        private List<BlockData> ConvertCentersToCameraSpace(List<BlockData> blocks)
        {
            int depthWidth = _currentDepthFrame.FrameDescription.Width;
            int depthHeight = _currentDepthFrame.FrameDescription.Height;
            ushort[] depths = new ushort[depthWidth * depthHeight];
            _currentDepthFrame.CopyFrameDataToArray(depths);

            int colorWidth = _colorFrameDescription.Width;
            int colorHeight = _colorFrameDescription.Height;
            CameraSpacePoint[] cameraPoints = new CameraSpacePoint[colorWidth * colorHeight];
            _coordinateMapper.MapColorFrameToCameraSpace(depths, cameraPoints);

            foreach (var block in blocks)
            {
                var center = new Point(block.centerX, block.centerY);
                var viableIdx = -1;
                var foundViableIndex = false;

                // Find a nearby point for which the depth is actually defined, as depth resolution is smaller than color resolution -> not all color points have a depth
                for (var i = -20; i < 20; i++)
                {
                    for (var j = -20; j < 20; j++)
                    {
                        var colorIdx = (center.Y + j) * colorWidth + (center.X + i);

                        if (colorIdx <= 0 || colorIdx >= cameraPoints.Length ||
                            double.IsNegativeInfinity(cameraPoints[colorIdx].X) ||
                            double.IsNegativeInfinity(cameraPoints[colorIdx].Y) ||
                            double.IsNegativeInfinity(cameraPoints[colorIdx].Z)) continue;
                        viableIdx = colorIdx;
                        foundViableIndex = true;
                        break;
                    }

                    if (foundViableIndex)
                    {
                        break;
                    }
                }

                double cameraX = foundViableIndex ? Convert.ToDouble(cameraPoints[viableIdx].X) : 0;
                double cameraY = foundViableIndex ? Convert.ToDouble(cameraPoints[viableIdx].Y) : 0;
                double currDepth = foundViableIndex ? Convert.ToDouble(cameraPoints[viableIdx].Z) : 0;


                block.cameraSpaceCenterX = cameraX;
                block.cameraSpaceCenterY = cameraY;
                block.cameraSpaceDepth = currDepth;
            }

            return blocks;
        }
    }
}
