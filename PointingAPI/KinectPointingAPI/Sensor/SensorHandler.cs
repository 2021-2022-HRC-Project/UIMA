using Microsoft.Kinect;
using System;
using System.Threading;
using System.Web;

namespace KinectPointingAPI.Sensor
{
    public static class SensorHandler
    {
        private const int ConnectTimeoutMs = 20000;

        public static KinectSensor GetSensor()
        {
            if (HttpContext.Current.Session["Sensor"] == null)
            {
                KinectSensor sensor = KinectSensor.GetDefault();
                sensor.Open();

                int msSlept = 0;
                while (!sensor.IsAvailable)
                {
                    Thread.Sleep(5);
                    msSlept += 5;
                    if (msSlept >= ConnectTimeoutMs)
                    {
                        Environment.Exit(-1);
                    }
                }

                HttpContext.Current.Session["Sensor"] = sensor;
                HttpContext.Current.Session["ColorFrameReader"] = sensor.ColorFrameSource.OpenReader();
                HttpContext.Current.Session["BodyFrameReader"] = sensor.BodyFrameSource.OpenReader();
                HttpContext.Current.Session["DepthFrameReader"] = sensor.DepthFrameSource.OpenReader();
                HttpContext.Current.Session["CoordinateMapper"] = sensor.CoordinateMapper;
            }

            return (KinectSensor)HttpContext.Current.Session["Sensor"];
        }

        public static ColorFrame GetColorFrame()
        {
            GetSensor();
            return ((ColorFrameReader)HttpContext.Current.Session["ColorFrameReader"]).AcquireLatestFrame();
        }

        public static BodyFrame GetBodyFrame()
        {
            GetSensor();
            return ((BodyFrameReader)HttpContext.Current.Session["BodyFrameReader"]).AcquireLatestFrame();
        }

        public static DepthFrame GetDepthFrame()
        {
            GetSensor();
            return ((DepthFrameReader)HttpContext.Current.Session["DepthFrameReader"]).AcquireLatestFrame();
        }
    }
}
