package edu.rosehulman.aixprize.pipeline.http;

import java.io.*;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.json.*;

public class HttpConfigurationLoader {
	protected static final String CONFIG_FILE_NAME = "desc/servers.json";

	public static class NoConfigurationFound extends Exception {
		private static final long serialVersionUID = -7148498252286690391L;

		public NoConfigurationFound(Class<? extends HttpAnnotator> annotator) {
			super("No configuration loaded for " + annotator.getName());
		}
	}


	private static HttpConfigurationLoader self;

	public synchronized static HttpConfigurationLoader getInstance() {
		if (self == null)
			self = new HttpConfigurationLoader();

		return self;
	}


	private Map<Class<?>, String> addressMap;
	private Map<Class<?>, Integer> portMap;
	private Map<Class<?>, String> pathMap;

	private HttpConfigurationLoader() {
		addressMap = new HashMap<>();
		portMap = new HashMap<>();
		pathMap = new HashMap<>();

		fillMaps(loadFileJSON());
	}

	private JSONObject loadFileJSON() {
		JSONObject json;

		try {
			json = new JSONObject(IOUtils.toString(new FileReader(CONFIG_FILE_NAME)));
		} catch (IOException e) {
			e.printStackTrace();
			json = new JSONObject();
		}

		return json;
	}

	private void fillMaps(JSONObject confJson) {
		confJson.keys().forEachRemaining(key -> {
			try {
				Class<?> keyClass = Class.forName(key);
				JSONObject annotatorObj = confJson.getJSONObject(key);

				addressMap.put(keyClass, annotatorObj.getString("address"));
				portMap.put(keyClass, annotatorObj.getInt("port"));
				pathMap.put(keyClass, annotatorObj.getString("path"));
			} catch (ClassNotFoundException | JSONException e) {
				e.printStackTrace();
			}
		});
	}

	public String getAddress(Class<? extends HttpAnnotator> annotator)
		throws NoConfigurationFound {
		if (!addressMap.containsKey(annotator))
			throw new NoConfigurationFound(annotator);
		return addressMap.get(annotator);
	}

	public int getPort(Class<? extends HttpAnnotator> annotator)
			throws NoConfigurationFound {
		if (!portMap.containsKey(annotator))
			throw new NoConfigurationFound(annotator);

		return portMap.get(annotator);
	}
	
	public String getPath(Class<? extends HttpAnnotator> annotator)
			throws NoConfigurationFound {
			if (!pathMap.containsKey(annotator))
				throw new NoConfigurationFound(annotator);
			return pathMap.get(annotator);
		}
}
