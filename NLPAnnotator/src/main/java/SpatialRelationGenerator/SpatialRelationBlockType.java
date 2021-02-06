package SpatialRelationGenerator;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import annotatorServer.AnnotationType;

public class SpatialRelationBlockType extends AnnotationType{

	List<OutputBlock> blocks;
	public SpatialRelationBlockType(String name, List<OutputBlock> blocks) {
		super(name);
		this.blocks = blocks;
	}

	@Override
	public List<String> getFields() {
		//unsure if can use toJson here may not play well when going to json later for final annotation
		Gson gson = new Gson();
		List<String> output = new ArrayList<>();
		
		for(OutputBlock b : this.blocks)
		{
			output.add(gson.toJson(b));
		}
		
		return output;
	}

}
