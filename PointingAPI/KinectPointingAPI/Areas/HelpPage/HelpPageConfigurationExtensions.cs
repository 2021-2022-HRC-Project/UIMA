using KinectPointingAPI.Areas.HelpPage.ModelDescriptions;
using KinectPointingAPI.Areas.HelpPage.Models;
using KinectPointingAPI.Areas.HelpPage.SampleGeneration;
using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Diagnostics;
using System.Globalization;
using System.Linq;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Controllers;
using System.Web.Http.Description;

namespace KinectPointingAPI.Areas.HelpPage
{
    public static class HelpPageConfigurationExtensions
    {
        private const string ApiModelPrefix = "MS_HelpPageApiModel_";

        /// <summary>
        /// Gets the help page sample generator.
        /// </summary>
        /// <param name="config">The <see cref="HttpConfiguration"/>.</param>
        /// <returns>The help page sample generator.</returns>
        public static HelpPageSampleGenerator GetHelpPageSampleGenerator(this HttpConfiguration config)
        {
            return (HelpPageSampleGenerator)config.Properties.GetOrAdd(
                typeof(HelpPageSampleGenerator),
                k => new HelpPageSampleGenerator());
        }

        /// <summary>
        /// Gets the model description generator.
        /// </summary>
        /// <param name="config">The configuration.</param>
        /// <returns>The <see cref="ModelDescriptionGenerator"/></returns>
        public static ModelDescriptionGenerator GetModelDescriptionGenerator(this HttpConfiguration config)
        {
            return (ModelDescriptionGenerator)config.Properties.GetOrAdd(
                typeof(ModelDescriptionGenerator),
                k => InitializeModelDescriptionGenerator(config));
        }

        /// <summary>
        /// Gets the model that represents an API displayed on the help page. The model is initialized on the first call and cached for subsequent calls.
        /// </summary>
        /// <param name="config">The <see cref="HttpConfiguration"/>.</param>
        /// <param name="apiDescriptionId">The <see cref="ApiDescription"/> ID.</param>
        /// <returns>
        /// An <see cref="HelpPageApiModel"/>
        /// </returns>
        public static HelpPageApiModel GetHelpPageApiModel(this HttpConfiguration config, string apiDescriptionId)
        {
            string modelId = ApiModelPrefix + apiDescriptionId;
            if (!config.Properties.TryGetValue(modelId, out object model))
            {
                Collection<ApiDescription> apiDescriptions = config.Services.GetApiExplorer().ApiDescriptions;
                ApiDescription apiDescription = apiDescriptions.FirstOrDefault(api => String.Equals(api.GetFriendlyId(), apiDescriptionId, StringComparison.OrdinalIgnoreCase));
                if (apiDescription != null)
                {
                    model = GenerateApiModel(apiDescription, config);
                    config.Properties.TryAdd(modelId, model);
                }
            }

            return (HelpPageApiModel)model;
        }

        private static HelpPageApiModel GenerateApiModel(ApiDescription apiDescription, HttpConfiguration config)
        {
            HelpPageApiModel apiModel = new HelpPageApiModel
            {
                ApiDescription = apiDescription,
            };

            ModelDescriptionGenerator modelGenerator = config.GetModelDescriptionGenerator();
            HelpPageSampleGenerator sampleGenerator = config.GetHelpPageSampleGenerator();
            GenerateUriParameters(apiModel, modelGenerator);
            GenerateRequestModelDescription(apiModel, modelGenerator, sampleGenerator);
            GenerateResourceDescription(apiModel, modelGenerator);
            GenerateSamples(apiModel, sampleGenerator);

            return apiModel;
        }

        private static void GenerateUriParameters(HelpPageApiModel apiModel, ModelDescriptionGenerator modelGenerator)
        {
            ApiDescription apiDescription = apiModel.ApiDescription;
            foreach (ApiParameterDescription apiParameter in apiDescription.ParameterDescriptions)
            {
                if (apiParameter.Source == ApiParameterSource.FromUri)
                {
                    HttpParameterDescriptor parameterDescriptor = apiParameter.ParameterDescriptor;
                    Type parameterType = null;
                    ModelDescription typeDescription = null;
                    ComplexTypeModelDescription complexTypeDescription = null;
                    if (parameterDescriptor != null)
                    {
                        parameterType = parameterDescriptor.ParameterType;
                        typeDescription = modelGenerator.GetOrCreateModelDescription(parameterType);
                        complexTypeDescription = typeDescription as ComplexTypeModelDescription;
                    }

                    // Example:
                    // [TypeConverter(typeof(PointConverter))]
                    // public class Point
                    // {
                    //     public Point(int x, int y)
                    //     {
                    //         X = x;
                    //         Y = y;
                    //     }
                    //     public int X { get; set; }
                    //     public int Y { get; set; }
                    // }
                    // Class Point is bindable with a TypeConverter, so Point will be added to UriParameters collection.
                    // 
                    // public class Point
                    // {
                    //     public int X { get; set; }
                    //     public int Y { get; set; }
                    // }
                    // Regular complex class Point will have properties X and Y added to UriParameters collection.
                    if (complexTypeDescription != null
                        && !IsBindableWithTypeConverter(parameterType))
                    {
                        foreach (ParameterDescription uriParameter in complexTypeDescription.Properties)
                        {
                            apiModel.UriParameters.Add(uriParameter);
                        }
                    }
                    else if (parameterDescriptor != null)
                    {
                        ParameterDescription uriParameter =
                            AddParameterDescription(apiModel, apiParameter, typeDescription);

                        if (!parameterDescriptor.IsOptional)
                        {
                            uriParameter.Annotations.Add(new ParameterAnnotation { Documentation = "Required" });
                        }

                        object defaultValue = parameterDescriptor.DefaultValue;
                        if (defaultValue != null)
                        {
                            uriParameter.Annotations.Add(new ParameterAnnotation { Documentation = "Default value is " + Convert.ToString(defaultValue, CultureInfo.InvariantCulture) });
                        }
                    }
                    else
                    {
                        Debug.Assert(parameterDescriptor == null);

                        // If parameterDescriptor is null, this is an undeclared route parameter which only occurs
                        // when source is FromUri. Ignored in request model and among resource parameters but listed
                        // as a simple string here.
                        ModelDescription modelDescription = modelGenerator.GetOrCreateModelDescription(typeof(string));
                        AddParameterDescription(apiModel, apiParameter, modelDescription);
                    }
                }
            }
        }

        private static bool IsBindableWithTypeConverter(Type parameterType)
        {
            if (parameterType == null)
            {
                return false;
            }

            return TypeDescriptor.GetConverter(parameterType).CanConvertFrom(typeof(string));
        }

        private static ParameterDescription AddParameterDescription(HelpPageApiModel apiModel,
            ApiParameterDescription apiParameter, ModelDescription typeDescription)
        {
            ParameterDescription parameterDescription = new ParameterDescription
            {
                Name = apiParameter.Name,
                Documentation = apiParameter.Documentation,
                TypeDescription = typeDescription,
            };

            apiModel.UriParameters.Add(parameterDescription);
            return parameterDescription;
        }

        private static void GenerateRequestModelDescription(HelpPageApiModel apiModel, ModelDescriptionGenerator modelGenerator, HelpPageSampleGenerator sampleGenerator)
        {
            ApiDescription apiDescription = apiModel.ApiDescription;
            foreach (ApiParameterDescription apiParameter in apiDescription.ParameterDescriptions)
            {
                if (apiParameter.Source == ApiParameterSource.FromBody)
                {
                    Type parameterType = apiParameter.ParameterDescriptor.ParameterType;
                    apiModel.RequestModelDescription = modelGenerator.GetOrCreateModelDescription(parameterType);
                    apiModel.RequestDocumentation = apiParameter.Documentation;
                }
                else if (apiParameter.ParameterDescriptor != null &&
                    apiParameter.ParameterDescriptor.ParameterType == typeof(HttpRequestMessage))
                {
                    Type parameterType = sampleGenerator.ResolveHttpRequestMessageType(apiDescription);

                    if (parameterType != null)
                    {
                        apiModel.RequestModelDescription = modelGenerator.GetOrCreateModelDescription(parameterType);
                    }
                }
            }
        }

        private static void GenerateResourceDescription(HelpPageApiModel apiModel, ModelDescriptionGenerator modelGenerator)
        {
            ResponseDescription response = apiModel.ApiDescription.ResponseDescription;
            Type responseType = response.ResponseType != null ? response.ResponseType : response.DeclaredType;
            if (responseType != null && responseType != typeof(void))
            {
                apiModel.ResourceDescription = modelGenerator.GetOrCreateModelDescription(responseType);
            }
        }

        private static void GenerateSamples(HelpPageApiModel apiModel, HelpPageSampleGenerator sampleGenerator)
        {
            try
            {
                foreach (var item in sampleGenerator.GetSampleRequests(apiModel.ApiDescription))
                {
                    apiModel.SampleRequests.Add(item.Key, item.Value);
                    LogInvalidSampleAsError(apiModel, item.Value);
                }

                foreach (var item in sampleGenerator.GetSampleResponses(apiModel.ApiDescription))
                {
                    apiModel.SampleResponses.Add(item.Key, item.Value);
                    LogInvalidSampleAsError(apiModel, item.Value);
                }
            }
            catch (Exception e)
            {
                apiModel.ErrorMessages.Add(string.Format(CultureInfo.CurrentCulture,
                    "An exception has occurred while generating the sample. Exception message: {0}",
                    HelpPageSampleGenerator.UnwrapException(e).Message));
            }
        }

        private static bool TryGetResourceParameter(ApiDescription apiDescription, HttpConfiguration config, out ApiParameterDescription parameterDescription, out Type resourceType)
        {
            parameterDescription = apiDescription.ParameterDescriptions.FirstOrDefault(
                p => p.Source == ApiParameterSource.FromBody ||
                    (p.ParameterDescriptor != null && p.ParameterDescriptor.ParameterType == typeof(HttpRequestMessage)));

            if (parameterDescription == null)
            {
                resourceType = null;
                return false;
            }

            resourceType = parameterDescription.ParameterDescriptor.ParameterType;

            if (resourceType == typeof(HttpRequestMessage))
            {
                HelpPageSampleGenerator sampleGenerator = config.GetHelpPageSampleGenerator();
                resourceType = sampleGenerator.ResolveHttpRequestMessageType(apiDescription);
            }

            if (resourceType == null)
            {
                parameterDescription = null;
                return false;
            }

            return true;
        }

        private static ModelDescriptionGenerator InitializeModelDescriptionGenerator(HttpConfiguration config)
        {
            ModelDescriptionGenerator modelGenerator = new ModelDescriptionGenerator(config);
            Collection<ApiDescription> apis = config.Services.GetApiExplorer().ApiDescriptions;
            foreach (ApiDescription api in apis)
            {
                if (TryGetResourceParameter(api, config, out _, out var parameterType))
                {
                    modelGenerator.GetOrCreateModelDescription(parameterType);
                }
            }
            return modelGenerator;
        }

        private static void LogInvalidSampleAsError(HelpPageApiModel apiModel, object sample)
        {
            if (sample is InvalidSample invalidSample)
            {
                apiModel.ErrorMessages.Add(invalidSample.ErrorMessage);
            }
        }
    }
}
