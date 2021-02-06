package Feedback;

import org.json.JSONArray;
import org.json.JSONObject;

import MetadataCompiler.MetaBlock;
import MetadataCompiler.MetadataAnnotationType;
import MetadataCompiler.OutputBlock;
import annotatorServer.Annotator;

public class ConfidenceFeedbackAnnotator extends Annotator {
	
	private double confidenceValue;
//	private MetaBlock selectedBlock;
	//Kinect confidences range from 0 to 1 (inclusive)
	private final static double CONFIDENCE_THRESHOLD = 0.5;

	@Override
	public String process(String request) {
		parseJson(request);
		String feedback = evaluateConfidence();
		
		ConfidenceFeedbackAnnotationType annotation= new ConfidenceFeedbackAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.ConfidenceFeedback\"",  feedback);
		
		System.out.println("{" + annotation.getName() + ": [{\"feedbackMsg\" : "+ annotation.feedbackMsg + "}]}");
		
		return "{" + annotation.getName() + ": [{\"feedbackMsg\" : "+ annotation.feedbackMsg + "}]}";
	}

	private void parseJson(String request) {
		JSONObject jsonObj = new JSONObject(request);
//		JSONArray jsonArray = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("MetadataSelectedBlock");
//		
//		JSONObject block = jsonArray.getJSONObject(0);
		
//		this.selectedBlock = new MetaBlock(block.getInt("id"),
//				block.getDouble("x"),
//				block.getDouble("y"),
//				block.getDouble("z"),
//				block.getString("name"));
		
//		this.confidenceValue = block.getDouble("confidenceValue");
		
		JSONArray jsonArray = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("AggregateConfidence");
		JSONObject confidence = jsonArray.getJSONObject(0);
		this.confidenceValue = confidence.getDouble("confidence");
	}
	
	private String evaluateConfidence() {
		if(this.confidenceValue >= this.CONFIDENCE_THRESHOLD) {
			return "True";
		} else {
			return "Is this the block you want?";
		}
	}
	

}
