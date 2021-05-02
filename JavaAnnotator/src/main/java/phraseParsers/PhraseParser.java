package phraseParsers;

import org.json.JSONObject;

import dataStructures.SpokenPhrase;

public interface PhraseParser {

	boolean findInformation(SpokenPhrase phrase, JSONObject object);
}
