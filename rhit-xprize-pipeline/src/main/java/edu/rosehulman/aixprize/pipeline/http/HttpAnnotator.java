package edu.rosehulman.aixprize.pipeline.http;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.*;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.json.JsonCasSerializer;
import org.apache.uima.resource.ResourceInitializationException;
import org.json.*;

import edu.rosehulman.aixprize.pipeline.http.HttpConfigurationLoader.NoConfigurationFound;

public abstract class HttpAnnotator extends JCasAnnotator_ImplBase {
	public static class NoMatchingAnnotationException extends Exception {
		private static final long serialVersionUID = 7484866497315133495L;
	}

	private String uri;
	private CloseableHttpClient client;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		HttpConfigurationLoader configurationLoader = HttpConfigurationLoader.getInstance();

		try {
			this.uri = new URIBuilder().setHost(configurationLoader.getAddress(this.getClass()))
									   .setScheme("http")
									   .setPort(configurationLoader.getPort(this.getClass()))
									   .setPath(configurationLoader.getPath(this.getClass()))
									   .build().toString();
			this.client = HttpClientBuilder.create().build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(JCas cas) throws AnalysisEngineProcessException {
		if (this.uri == null) {
			return;
		}
		
		System.out.println(uri.toString());
		try {
			RequestBuilder requestBuilder = RequestBuilder.post(uri);
			requestBuilder.setEntity(encodeCas(cas));
			receiveAnnotations(cas, this.client.execute(requestBuilder.build()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HttpEntity encodeCas(JCas cas) throws IOException {
		StringWriter serialized = new StringWriter();
		JsonCasSerializer.jsonSerialize(cas.getCas(), serialized);
		return new StringEntity(serialized.toString(), ContentType.APPLICATION_JSON);
	}

	private void receiveAnnotations(JCas cas, HttpResponse resp) throws IOException {
		InputStream stream = resp.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		JSONObject annotationJson;

		annotationJson = new JSONObject(reader.readLine());
		for (String annotationName : JSONObject.getNames(annotationJson)) {
			JSONArray jsonAnnotations = annotationJson.getJSONArray(annotationName);
			List<Annotation> annotations = new ArrayList<>();
			for (int i = 0; i < jsonAnnotations.length(); i++) {
				try {
					annotations.add(this.createAnnotation(cas, annotationName, jsonAnnotations.getJSONObject(i)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			annotations.forEach(Annotation::addToIndexes);
		}
	}

	protected Annotation createAnnotation(JCas cas, String annotationName, JSONObject annotationJson)
			throws Exception {
		Annotation annotation = (Annotation)Class.forName(annotationName).getConstructor(JCas.class).newInstance(cas);

		annotationJson.keys().forEachRemaining(field -> addFieldToAnnotation(cas, annotationJson, annotation, field));

		return annotation;
	}

	protected void addFieldToAnnotation(JCas cas, JSONObject annotationJson, Annotation annotation, String field) {
		try {
			Feature feature = cas.getRequiredFeature(annotation.getType(), field);
			annotation.setFeatureValueFromString(feature, annotationJson.get(field).toString());
		} catch (CASException e) {
			e.printStackTrace();
		}
	}
}
