package phraseParsers;

import org.json.JSONObject;

import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class NameParser implements PhraseParser{


	private static final String JSON_KEY = "Name";

	@Override
	public boolean findInformation(SpokenPhrase phrase, JSONObject object) {
		
		for(int i = phrase.sentence.size() - 1; i >= 0; i--){
			WordProperties word = phrase.sentence.get(i);
			if(word.isProper && word.isSingular && word.partOfSpeech.equalsIgnoreCase("NOUN")){
				object.put(JSON_KEY, word.lemma);
				return true;
			}
		}
		object.put(JSON_KEY, "");
		return false;
	}
}
