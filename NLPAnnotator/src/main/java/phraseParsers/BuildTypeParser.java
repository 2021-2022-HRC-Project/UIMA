package phraseParsers;

import java.util.Arrays;

import org.json.JSONObject;

import dataStructures.Construct;
import dataStructures.ConstructContainer;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class BuildTypeParser implements PhraseParser {
	
	
	private ConstructContainer savedConstructs;
	
	public BuildTypeParser(ConstructContainer savedConstructs) {
		this.savedConstructs = savedConstructs;
	}
	
	@Override
	public boolean findInformation(SpokenPhrase phrase, JSONObject object) {
		// TODO Auto-generated method stub
		
		for(int i = phrase.sentence.size() - 1; i >= 0; i--){
			WordProperties word = phrase.sentence.get(i);
			if(word.partOfSpeech.equalsIgnoreCase("Noun") && word.isSingular){
				Construct match = savedConstructs.findMatching(word.lemma);
				if(match != null){
					object.put("Build_Type", match.identifier);
					object.put("Build_With", match.material);
					return true;
				}
			}
		}
		
		object.put("Build_Type", "Unidentified");
		object.put("Build_With", Arrays.asList());
		return false;
	}

}
