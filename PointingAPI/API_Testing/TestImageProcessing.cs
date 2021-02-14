using Emgu.CV;
using Emgu.CV.Structure;
using KinectPointingAPI.Image_Processing;
using KinectPointingAPI.Utilities;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Drawing;

namespace API_Testing
{
    [TestClass]
    public class TestImageProcessing
    {

        BlockDetector _detector;

        [TestInitialize]
        public void SetUp()
        {
            _detector = new BlockDetector();
        }

        [TestMethod]
        public void TestFillMask_EmptyImage()
        {
            Image<Gray, Byte> inputImg = new Image<Gray, byte>(3, 3);

            Image<Gray, Byte> expectedImg = inputImg.Clone();
            Image<Gray, Byte> actualImg = _detector.FillMask(inputImg);

            AssertImagesEqual(expectedImg, actualImg);
        }

        [TestMethod]
        public void TestFillMask_NoHoles()
        {
            Image<Gray, byte> inputImg = new Image<Gray, byte>(3, 3)
            {
                [0, 0] = new Gray(255),
                [1, 0] = new Gray(255),
                [2, 0] = new Gray(255),
                [2, 1] = new Gray(255),
                [2, 2] = new Gray(255)
            };
            // Create L pattern in image

            Image<Gray, Byte> expectedImg = inputImg.Clone();
            Image<Gray, Byte> actualImg = _detector.FillMask(inputImg);

            AssertImagesEqual(expectedImg, actualImg);
        }

        [TestMethod]
        public void TestFillMask_OneHole()
        {
            Image<Gray, Byte> inputImg = new Image<Gray, byte>(3, 3)
            {
                [0, 0] = new Gray(255),
                [0, 1] = new Gray(255),
                [0, 2] = new Gray(255),
                [1, 0] = new Gray(255),
                [1, 2] = new Gray(255),
                [2, 0] = new Gray(255),
                [2, 1] = new Gray(255),
                [2, 2] = new Gray(255)
            };
            // Create hollow square pattern in image

            Image<Gray, Byte> expectedImg = inputImg.Clone();
            expectedImg[1, 1] = new Gray(255);
            Image<Gray, Byte> actualImg = _detector.FillMask(inputImg);

            AssertImagesEqual(expectedImg, actualImg);
        }

        [TestMethod]
        public void TestFindColorAtCenter_NoCenters()
        {
            Point[] blockCenters = new Point[1];
            Image<Bgra, Byte> inputImg = new Image<Bgra, byte>(3, 3) {[1, 1] = new Bgra(255, 255, 255, 0)};

            List<BlockData> actualColors = _detector.FindColorAtCenters(blockCenters, inputImg);

            AssertColorsMatch(blockCenters, inputImg, actualColors);
        }

        [TestMethod]
        public void TestFindColorAtCenter_SingleCenterDefaultColor()
        {
            Point[] blockCenters = {
                new Point(1, 1)
            };
            Image<Bgra, Byte> inputImg = new Image<Bgra, byte>(3, 3);

            List<BlockData> actualColors = _detector.FindColorAtCenters(blockCenters, inputImg);

            AssertColorsMatch(blockCenters, inputImg, actualColors);
        }

        [TestMethod]
        public void TestFindColorAtCenter_SingleCenterRedColor()
        {
            Point[] blockCenters = {
                new Point(1, 1)
            };
            Image<Bgra, Byte> inputImg = new Image<Bgra, byte>(3, 3) {[1, 1] = new Bgra(10, 10, 200, 0)};

            List<BlockData> actualColors = _detector.FindColorAtCenters(blockCenters, inputImg);

            AssertColorsMatch(blockCenters, inputImg, actualColors);
        }

        [TestMethod]
        public void TestFindColorAtCenter_SingleCenterMaxColor()
        {
            Point[] blockCenters = {
                new Point(1, 1)
            };
            Image<Bgra, Byte> inputImg = new Image<Bgra, byte>(3, 3) {[1, 1] = new Bgra(255, 255, 255, 0)};

            List<BlockData> actualColors = _detector.FindColorAtCenters(blockCenters, inputImg);

            AssertColorsMatch(blockCenters, inputImg, actualColors);
        }

        [TestMethod]
        public void TestFindColorAtCenter_MultipleCenters()
        {
            Point[] blockCenters = {
                new Point(1, 1),
                new Point(2, 0)
            };
            Image<Bgra, Byte> inputImg = new Image<Bgra, byte>(3, 3)
            {
                [0, 2] = new Bgra(255, 255, 255, 0), [1, 1] = new Bgra(105, 5, 58, 0)
            };

            List<BlockData> actualColors = _detector.FindColorAtCenters(blockCenters, inputImg);

            AssertColorsMatch(blockCenters, inputImg, actualColors);
        }

        private void AssertImagesEqual(Image<Gray, Byte> expected, Image<Gray, Byte> actual)
        {
            Image<Gray, Byte> imageDiff = expected.AbsDiff(actual);

            Gray expectedPixelVal = new Gray(0);
            for (int row = 0; row < imageDiff.Rows; row++)
            {
                for (int col = 0; col < imageDiff.Cols; col++)
                {
                    Assert.AreEqual(expectedPixelVal, imageDiff[row, col],
                        $"Mismatch found at entry with row = {row}, col = {col}.");
                }
            }
        }

        private void AssertColorsMatch(Point[] blockCenters, Image<Bgra, Byte> inputImg, List<BlockData> actualColors)
        {
            Assert.AreEqual(blockCenters.Length, actualColors.Count, "Number of items returned does not equal the number of centers provided.");
            for (int i = 0; i < blockCenters.Length; i++)
            {
                Point currentPoint = blockCenters[i];
                int row = currentPoint.Y;
                int col = currentPoint.X;
                Bgra expectedColor = inputImg[row, col];

                BlockData currentBlock = actualColors[i];
                Bgra actualColor = new Bgra(currentBlock.bHueVal, currentBlock.gHueVal, currentBlock.rHueVal, 0);

                Assert.AreEqual(expectedColor, actualColor, "Mismatch found at point {0}, which corresponds to row = {1}, col = {2}.", i + 1, row, col);
            }
        }
    }
}
