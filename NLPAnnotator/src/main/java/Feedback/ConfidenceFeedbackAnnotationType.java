package Feedback;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import MetadataCompiler.OutputBlock;
import annotatorServer.AnnotationType;

public class ConfidenceFeedbackAnnotationType extends AnnotationType {

//	OutputBlock chosenBlock;
	String feedbackMsg;

	public ConfidenceFeedbackAnnotationType(String name, String feedback) {
		super(name);
//		this.chosenBlock = finalBlock;
		this.feedbackMsg = feedback;
	}

	@Override
	public List<String> getFields() {
		List<String> output = new ArrayList<>();
		Gson gson = new Gson();

//		output.add(gson.toJson(this.chosenBlock));
		output.add(gson.toJson(this.feedbackMsg));

		return output;
	}

}
