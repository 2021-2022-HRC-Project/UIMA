using Newtonsoft.Json.Linq;
using System;
using System.Diagnostics;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Results;

namespace KinectPointingAPI.Controllers
{
    public abstract class AnnotationController<T> : ApiController
    {
        [HttpPost]
        [Route("")]
        public JsonResult<T> Post()
        {
            var casJson = ParsePostBody();
            ProcessRequest(casJson);
            return GenerateAnnotationResponse();
        }

        private JToken ParsePostBody()
        {
            Task<string> task = GetPostBody();

            string casJson = "";
            try
            {
                task.Wait();
                casJson = task.Result;
            }
            catch
            {
                Environment.Exit(-1);
            }

            JObject payloadContent = JObject.Parse(casJson);
            Debug.Write(payloadContent);
            JToken allAnnotations = payloadContent["_views"]?["_InitialView"];

            return allAnnotations;
        }

        private async Task<string> GetPostBody()
        {
            return await Request.Content.ReadAsStringAsync();
        }

        public abstract void ProcessRequest(JToken allAnnotations);

        public abstract JsonResult<T> GenerateAnnotationResponse();
    }
}
