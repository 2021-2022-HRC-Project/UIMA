package MetadataCompiler;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import annotatorServer.AnnotationType;

public class MetadataAnnotationType extends AnnotationType {
	
	OutputBlock chosenBlock;

	public MetadataAnnotationType(String name, OutputBlock finalBlock) {
		super(name);
		
		this.chosenBlock = finalBlock;
	}

	@Override
	public List<String> getFields() {
		List<String> output = new ArrayList<>();
		Gson gson = new Gson();
		
		output.add(gson.toJson(this.chosenBlock));
		return output;
	}
}
