using Newtonsoft.Json.Linq;
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
            JToken casJSON = this.ParsePostBody();
            this.ProcessRequest(casJSON);
            return this.GenerateAnnotationResponse();
        }

        private JToken ParsePostBody()
        {
            Task<string> task = this.GetPostBody();

            string casJSON = "";
            try
            {
                task.Wait();
                casJSON = task.Result;
            }
            catch
            {
                System.Environment.Exit(-1);
            }

            JObject payloadContent = JObject.Parse(casJSON);
            System.Diagnostics.Debug.Write(payloadContent);
            JToken allAnnotations = payloadContent["_views"]["_InitialView"];

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
