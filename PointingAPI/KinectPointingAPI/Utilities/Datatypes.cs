using System.Collections.Generic;

namespace KinectPointingAPI.Utilities
{
    public class BlockData
    {
        public int id;
        public int centerX;
        public int centerY;
        public double cameraSpaceCenterX;
        public double cameraSpaceCenterY;
        public double cameraSpaceDepth;
        public double rHueVal;
        public double gHueVal;
        public double bHueVal;

        public BlockData(int id, int centerX, int centerY, double rVal, double gVal, double bVal)
        {
            this.id = id;
            this.centerX = centerX;
            this.centerY = centerY;
            rHueVal = rVal;
            gHueVal = gVal;
            bHueVal = bVal;
        }

        public Dictionary<string, double> ConvertToDict()
        {
            Dictionary<string, double> serializedFormat = new Dictionary<string, double>
            {
                { "id", id },
                { "center_X", centerX },
                { "center_Y", centerY },
                { "camera_space_center_X", cameraSpaceCenterX },
                { "camera_space_center_Y", cameraSpaceCenterY },
                { "camera_space_depth", cameraSpaceDepth },
                { "r_hue", rHueVal },
                { "g_hue", gHueVal },
                { "b_hue", bHueVal }
            };

            return serializedFormat;
        }
    }
}