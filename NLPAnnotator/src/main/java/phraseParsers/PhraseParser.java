package phraseParsers;

import org.json.JSONObject;

import dataStructures.SpokenPhrase;

public interface PhraseParser {

	public boolean findInformation(SpokenPhrase phrase, JSONObject object);
}
