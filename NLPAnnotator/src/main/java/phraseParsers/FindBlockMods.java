package phraseParsers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import actionArtifacts.Directions;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class FindBlockMods implements PhraseParser{

	List<String> directions;
	public FindBlockMods() {
		directions = new ArrayList<>();
//		directions.add("above");
//		directions.add("below");
		directions.add("left");
		directions.add("right");
		directions.add("front");
		directions.add("behind");
		directions.add("next");
	}
	
	@Override
	public boolean findInformation(SpokenPhrase phrase, JSONObject object) {
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < phrase.sentence.size(); i ++){
			WordProperties word = phrase.sentence.get(i);
			if(word.partOfSpeech.equalsIgnoreCase("Noun")){
				
				JSONObject item = new JSONObject();
				item.put("Item", word.lemma);
				
				JSONArray mods = new JSONArray();
				
				for(int j = i-1; j >= 0; j--){
					WordProperties subWord = phrase.sentence.get(j);
					if(subWord.partOfSpeech.equalsIgnoreCase("ADJ") && subWord.parent.equals(word)){
						mods.put(subWord.lemma);
					}
					for(String dir: directions){
						if(dir.equalsIgnoreCase(subWord.lemma)){
							mods.put(subWord.lemma);
						}
					}
					if(subWord.partOfSpeech.equalsIgnoreCase("NOUN")){
						break;
					}
				}
				item.put("Mods", mods);
				
				if(mods.toList().size() != 0){
					jsonArray.put(item);
				}
				
			}
			
		}
		object.put("Object_Mods", jsonArray);
		return true;
		
	}
	
}
