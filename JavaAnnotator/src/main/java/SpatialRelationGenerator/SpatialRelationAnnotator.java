package SpatialRelationGenerator;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dataStructures.Annotator;

public class SpatialRelationAnnotator extends Annotator{

	double WIDTH_OF_WORKING_SPACE = 2;
	
	@Override
	public String process(String request) {
		List<InnerBlock> blocks = parseJson(request);
		
		Grapher grapher = new Grapher(blocks, WIDTH_OF_WORKING_SPACE);
		grapher.makeGraph(); //blocks spatial relationship fields are now populated
		
		List<OutputBlock> output = convertToOutputBlocks(blocks);
		
		//Convert to JSON and return
		SpatialRelationBlockType annotation = new SpatialRelationBlockType("\"edu.rosehulman.aixprize.pipeline.types.SpatialRelationBlock\"",output);

		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
	}
	
	public List<InnerBlock> parseJson (String request){
		List<InnerBlock> output = new ArrayList<InnerBlock>();
		
		//Parse blocks from JSON
		JSONObject jsonObj = new JSONObject(request);
		
		JSONArray blockData = null;
		
		try {
			blockData = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("DetectedBlock");
		} 
		catch(JSONException je) {
			System.err.println(je);
			return null;
		}
		
		System.out.println("SPACIAL RELATION DATA");

		// FIXME: Some of codes below will cause the "out of range" message during execution
		System.out.println(blockData);
		
		for(int i = 0; i < blockData.length(); i++){
			JSONObject block = blockData.getJSONObject(i);
			
			//create block from the jsondata (Block detection annotator: detected blocks)		
			InnerBlockBuilder builder = new InnerBlockBuilder();
			InnerBlock innerBlock = builder.withId(block.getInt("id"))
				.withX(block.getDouble("camera_space_center_X"))
				.withY(block.getDouble("camera_space_center_Y"))
				.withZ(block.getDouble("camera_space_depth"))
				.withName(""+block.getInt("id"))
				.build();
			
			output.add(innerBlock);
		}

		System.out.println("AFTER SPACIAL RELATION DATA");

		return output;
	}
	
	private List<OutputBlock> convertToOutputBlocks(List<InnerBlock> blocks) {
		//Convert inner blocks to output blocks for neat JSON
		List<OutputBlock> output = new ArrayList<>();
		for(InnerBlock b: blocks){
			output.add(new OutputBlock(b));
		}
		return output;
	}



}
