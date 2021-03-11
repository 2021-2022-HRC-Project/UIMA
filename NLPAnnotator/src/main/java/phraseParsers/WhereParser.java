package phraseParsers;

import org.json.JSONObject;

import dataStructures.SpokenPhrase;

public  class WhereParser implements PhraseParser {

	@Override
	public boolean findInformation(SpokenPhrase phrase, JSONObject object) {
		return false;
	}

}
