package phraseParsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class GestureParser implements PhraseParser {

	
	private static final String JSON_KEY = "Gesture";
	private static final List<String> gestureIndicators = Arrays.asList("this", "that");

	@Override
	public boolean findInformation(SpokenPhrase phrase, JSONObject object) {
		for(WordProperties word: phrase.sentence){
			if(word.dependencyEdge.equalsIgnoreCase("DET") 
					&& word.partOfSpeech.equalsIgnoreCase("DET")
					&& gestureIndicators.contains(word.lemma.toLowerCase())){
				object.put(JSON_KEY, true);
				return true;
			}
		}
		object.put(JSON_KEY, false);
		return false;
	}

}
