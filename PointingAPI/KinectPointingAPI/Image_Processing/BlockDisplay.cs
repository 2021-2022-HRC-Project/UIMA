using Emgu.CV;
using Emgu.CV.Structure;
using Emgu.CV.UI;
using KinectPointingAPI.Utilities;
using System;
using System.Drawing;

namespace KinectPointingAPI.Image_Processing
{
    public class BlockDisplay
    {
        private static readonly int OUTPUT_WIDTH = 640;
        private static readonly int OUTPUT_HEIGHT = 480;

        private static readonly int OUTPUT_WINDOW_WIDTH = 1920;
        private static readonly int OUTPUT_WINDOW_HEIGHT = 1080;

        public void DisplayBlockOnImage(Bitmap inputImg, BlockData blockToDisplay)
        {
            Image<Bgra, Byte> img = new Image<Bgra, Byte>(inputImg);

            Point blockCenter = new Point(blockToDisplay.centerX, blockToDisplay.centerY);
            int radius = 20;
            int filledCircle = -1;

            CvInvoke.Circle(img, blockCenter, radius, new MCvScalar(0, 0, 0, 255), filledCircle);
            DisplayImage(img);
        }

        private void DisplayImage(Image<Bgra, Byte> img)
        {
            ImageViewer viewer = new ImageViewer();
            CvInvoke.Resize(img, img, new Size(OUTPUT_WIDTH, OUTPUT_HEIGHT));

            viewer.Image = img;
            viewer.Size = new Size(OUTPUT_WINDOW_WIDTH, OUTPUT_WINDOW_HEIGHT);
            viewer.ShowDialog();
        }
    }
}