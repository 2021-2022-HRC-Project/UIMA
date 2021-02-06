package helloWorld;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import annotatorServer.Annotator;

public class JavaHelloWorldAnnotator extends Annotator{

	@Override
	public String process(String request) throws IOException {
		
		String text;
		
		//read in from the CAS object some information
		JSONObject jsonObj = new JSONObject(request);
		JSONArray pythonAnnotation = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("PythonHelloWorld");
		
		text = pythonAnnotation.getJSONObject(0).getString("text") + " " + "World";
		
		JavaHelloWorldAnnotationType annotation = 
				new JavaHelloWorldAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.JavaHelloWorld\"", text);
		
		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
	}

}
