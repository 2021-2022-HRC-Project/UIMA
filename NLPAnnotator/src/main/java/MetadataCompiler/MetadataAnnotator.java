package MetadataCompiler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CoreNLP.Models.ParseResultModel;
import Utils.NLPOutputParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dataStructures.Annotator;

public class MetadataAnnotator extends Annotator{

	List<String> relationKeywords;
	List<Integer> degrees;
	MetaBlock startBlock;

	public MetadataAnnotator() {
		super();
		relationKeywords = new ArrayList<>();
		degrees = new ArrayList<>();
	}


	@Override
	public String process(String request) {
		parseJson(request);

		MetadataCompiler compiler = new MetadataCompiler();

		MetaBlock output = null;
		try{
			output = compiler.chooseBlock(relationKeywords, degrees, startBlock);
		}
		catch(Exception e) {
			//TODO: Remove the following debug message when metadata annotator is working
			System.err.println("Debug Message 0");

			System.err.println(e);
			return null;
		}
		double pathConfidence = compiler.getPathConfidenceValue();

		OutputBlock finalBlock = new OutputBlock(output,pathConfidence);
		//Convert to JSON
		MetadataAnnotationType annotation= new MetadataAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock\"", finalBlock);

		System.out.println("MetaData Output");
		System.out.println("{" + annotation.getName() + ": "+ annotation.getFields() + "}");
		return "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
	}

	public void parseJson(String request){
//----------Loop to create all blocks in a Map of id's to MetaBlocks
		Map<Integer, MetaBlock> blocksFromJson = new HashMap<>();

		JSONObject jsonObj = new JSONObject(request);

//----------Find the relation keywords and degree

		//--------------------- test input ---------------------
//		this.degrees.add(1);
		//--------------------- test input ---------------------

		JSONArray NLPProcessor = null;


		try {
			NLPProcessor = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("NLPProcessor");
		}
		catch(JSONException je) {
			//TODO: Remove the following debug message when metadata annotator is working
			System.err.println("Debug Message 1");

			System.err.println(je);
			return;
		}

		String output = NLPProcessor.getJSONObject(0).getString("output").replaceAll("\\[", "").replaceAll("]", "");

		String directionString = NLPOutputParser.findValueOf(output, "Direction");
		System.out.println("directionString: " + directionString);

		String upperCaseDirectionString = directionString.toUpperCase();
		List<String> reverseOrderMods = new ArrayList<>();
		switch (upperCaseDirectionString){
			case "FRONT":
			case "LEFT":
			case "RIGHT":
			case "BEHIND":
				reverseOrderMods.add(upperCaseDirectionString);
			case "IN_FRONT_OF":
				reverseOrderMods.add("FRONT");
		}

		for(int i = reverseOrderMods.size() - 1; i >= 0; i--){
			this.relationKeywords.add(reverseOrderMods.get(i));
		}

		//_______________________TEMPORARY UNTIL NLP WORKING___________________

//		this.relationKeywords.add("LEFT");

		//_____________________________________________________________________

		JSONArray blockData = null;

		System.out.println("Before Blocks");
		try {
			blockData = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("SpatialRelationBlock");
		}
		catch (JSONException je) {
			//TODO: Remove the following debug message when metadata annotator is working
			System.err.println("Debug Message 2");
			
			System.err.println(je);
			return;
		}

		if(blockData.length() < 1) {
			System.err.println("No blocks detected:\n" + jsonObj);
			return;
		}

		for(int i = 0; i < blockData.length(); i++){
			JSONObject block = blockData.getJSONObject(i);

			//create block from the jsondata (SpacialRelationAnnotation)
			MetaBlock blockForMap = new MetaBlock(block.getInt("id"),
					block.getDouble("x"),
					block.getDouble("y"),
					block.getDouble("z"),
					block.getString("name"));

			blocksFromJson.put(blockForMap.id,blockForMap);
		}

		System.out.println("Before Blocks relations");
//----------Then Loop through JSON again, populate spatial relation lists of each block
		for(int i = 1; i <= blockData.length(); i++){
			JSONObject block = blockData.getJSONObject(i-1);

			String[] directions = {"left", "right", "behind", "front"};

			for(String dir : directions)
			{
				String leftList = block.getString(dir).trim();
				leftList = leftList.substring(1,leftList.length()-1);

				leftList = leftList.replaceAll("\\(", "");
				leftList = leftList.replaceAll("\\),", ")");
				String[] arrOfStr = leftList.split("\\)");

				for(String s : arrOfStr)
				{
					if(!s.equals("")){
						String[] idAndConfidence = s.trim().split(",");
						MetablockConfidenceTuple toadd = new MetablockConfidenceTuple(blocksFromJson.get(Integer.parseInt(idAndConfidence[0])),Double.parseDouble(idAndConfidence[1]));
						switch(dir){
							case "front":
								blocksFromJson.get(i).front.add(toadd);
								break;
							case "left":
								blocksFromJson.get(i).left.add(toadd);
								break;
							case "right":
								blocksFromJson.get(i).right.add(toadd);
								break;
							case "behind":
								blocksFromJson.get(i).behind.add(toadd);
								break;
						}
					}
				}
			}
		}
//----------Find the starting block
		System.out.println("Before pointing");
		JSONArray pointingData = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("Pointing");

		double maxConf = 0;
		int originId = 1;

		for(int i = 0; i < pointingData.length(); i++){
			JSONObject block = pointingData.getJSONObject(i);

			if(block.getDouble("confidence") > maxConf){
				maxConf = block.getDouble("confidence");
				originId = block.getInt("id");
			}
		}

		//-----select start block------
		this.startBlock = blocksFromJson.get(originId);
		System.out.println("Start Block: " + this.startBlock);
		System.out.println("BLOCKS: " + blocksFromJson);
		System.out.println("ORIGIN: " + originId);

	}
}
