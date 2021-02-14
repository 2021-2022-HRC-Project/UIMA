using KinectPointingAPI.Image_Processing;
using KinectPointingAPI.Utilities;
using Microsoft.Kinect;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Threading;
using System.Web.Http;
using System.Web.Http.Results;
using System.Windows;
using System.Windows.Media;
using System.Windows.Media.Imaging;

namespace KinectPointingAPI.Controllers
{
    [RoutePrefix("api/Output")]
    public class OutputController : AnnotationController<Dictionary<string, List<Dictionary<string, double>>>>
    {
        private KinectSensor _kinectSensor;
        private readonly FrameDescription _colorFrameDescription;
        private ColorFrame _currentColorFrame;
        private readonly BlockDisplay _blockDisplay;

        private int _optimalBlockId;
        private readonly List<Dictionary<string, double>> _allBlocks;

        private const int ConnectTimeoutMs = 20000;
        public static readonly string AnnotationTypeClass = "edu.rosehulman.aixprize.pipeline.types.FilteredBlock";

        public OutputController()
        {
            _kinectSensor = KinectSensor.GetDefault();
            _colorFrameDescription = _kinectSensor.ColorFrameSource.FrameDescription;

            _allBlocks = new List<Dictionary<string, double>>();
            _blockDisplay = new BlockDisplay();
        }

        public override void ProcessRequest(JToken allAnnotations)
        {
            _kinectSensor = KinectSensor.GetDefault();

            _optimalBlockId = GetHighestConfidenceId(allAnnotations);

            BlockData optimalBlock = GetBestBlock(allAnnotations);
            DisplayBestBlock(optimalBlock);
        }

        public override JsonResult<Dictionary<string, List<Dictionary<string, double>>>> GenerateAnnotationResponse()
        {
            List<Dictionary<string, double>> filteredBlocks = new List<Dictionary<string, double>>();

            foreach (Dictionary<string, double> blockDetails in _allBlocks)
            {
                int id = Convert.ToInt32(blockDetails["id"]);
                int isBestBlock = id == _optimalBlockId ? 1 : 0;

                blockDetails.Add("isSelectedBlock", isBestBlock);
                filteredBlocks.Add(blockDetails);
            }

            Dictionary<string, List<Dictionary<string, double>>> annotation = new Dictionary<string, List<Dictionary<string, double>>>
            {
                { AnnotationTypeClass, filteredBlocks }
            };
            return Json(annotation);
        }

        private int GetHighestConfidenceId(JToken allAnnotations)
        {
            double bestBlockConfidence = -1;
            var bestBlockId = -1;

            var confidenceDetails = allAnnotations["Pointing"];
            foreach (var blockString in confidenceDetails)
            {
                var blockDetails = new Dictionary<string, double>();

                if (blockString == null) continue;
                var id = blockString["id"].ToObject<int>();
                var confidence = blockString["confidence"].ToObject<double>();

                blockDetails.Add("id", id);
                _allBlocks.Add(blockDetails);

                if (!(confidence > bestBlockConfidence)) continue;
                bestBlockConfidence = confidence;
                bestBlockId = id;
            }

            return bestBlockId;
        }

        private BlockData GetBestBlock(JToken allAnnotations)
        {
            var detectedBlocks = allAnnotations["DetectedBlock"];
            BlockData bestBlock = null;
            foreach (var blockString in detectedBlocks)
            {
                if (blockString == null) continue;
                int id = blockString["id"].ToObject<int>();
                int centerX = blockString["center_X"].ToObject<int>();
                int centerY = blockString["center_Y"].ToObject<int>();
                double rHue = blockString["r_hue"].ToObject<double>();
                double gHue = blockString["g_hue"].ToObject<double>();
                double bHue = blockString["b_hue"].ToObject<double>();

                if (id == _optimalBlockId)
                {
                    bestBlock = new BlockData(id, centerX, centerY, rHue, gHue, bHue);
                    break;
                }
            }

            return bestBlock;
        }

        private void DisplayBestBlock(BlockData blockToDisplay)
        {
            _kinectSensor.Open();
            int timeSlept = 0;
            while (!_kinectSensor.IsAvailable)
            {
                Thread.Sleep(5);
                timeSlept += 5;
                if (timeSlept > ConnectTimeoutMs)
                {
                    Environment.Exit(-2);
                }
            }

            ColorFrameReader colorFrameReader = _kinectSensor.ColorFrameSource.OpenReader();

            bool dataReceived = false;
            while (!dataReceived)
            {
                Debug.WriteLine("About to acquire color frame for drawing!");
                _currentColorFrame = colorFrameReader.AcquireLatestFrame();
                if (_currentColorFrame != null)
                {
                    dataReceived = true;
                }
            }
            Debug.WriteLine("Found color frame for drawing!");
            Bitmap currFrame = ConvertCurrFrameToBitmap();
            _blockDisplay.DisplayBlockOnImage(currFrame, blockToDisplay);

            colorFrameReader.Dispose();
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
    }
}
