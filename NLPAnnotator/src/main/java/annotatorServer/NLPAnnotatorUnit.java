package annotatorServer;

import java.util.List;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Actions.VerbalAction;
import actionArtifacts.UnknownArtifact;
import dataStructures.SpokenPhrase;
import googleNLP.NLPTokenParser;


public class NLPAnnotatorUnit extends Annotator {

	public NLPTokenParser request;
	public List<VerbalAction> actions;
	public Gson gson;
	public String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.NLPProcessor\"";
	
	public NLPAnnotatorUnit(NLPTokenParser parser, List<VerbalAction> actions) {
		this.request = parser;
		this.actions = actions;
		this.gson = new Gson();
	}
	
	@Override
	public String process(String JSONRequest) {
		System.out.println(JSONRequest);
		JSONObject jsonObj = new JSONObject(JSONRequest);
		//Note there can be more than one text in the json
		String result =	jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("SpokenText").getJSONObject(0).getString("text");
		SpokenPhrase phrase = request.buildDependencyTree(result);
		String gsonString = "{" + unitWrapper + ": [";
		boolean found = false;
		for(VerbalAction action: actions){
			if(action.isAction(phrase)){
				found = true;
				System.out.println(action.parseImportant(phrase).getString());
				JSONObject obj = new JSONObject();
				obj.put("output", action.parseImportant(phrase).getString());
				gsonString += obj.toString();
				break;
			}
		}
		if(!found){
			gsonString += gson.toJson(new UnknownArtifact().getString());
		}
		gsonString += "]}";
		System.out.println("NLP result");
		System.out.println(gsonString);
		return gsonString;
	}

}
