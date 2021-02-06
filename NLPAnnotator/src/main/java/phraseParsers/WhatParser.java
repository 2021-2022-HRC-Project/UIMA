package phraseParsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

public class WhatParser implements PhraseParser {

	private static final List<String> triggers = Arrays.asList("with", "out of");
	@Override
	public boolean findInformation(SpokenPhrase phrase, JSONObject object) {
		if(specifiesMaterial(phrase)){
			
			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < phrase.sentence.size(); i ++){
				WordProperties word = phrase.sentence.get(i);
				System.out.println((word.partOfSpeech.equalsIgnoreCase("Noun") + "#" + possibleMaterial(word.lemma, object)));
				if(word.partOfSpeech.equalsIgnoreCase("Noun") && possibleMaterial(word.lemma, object)){
					
					JSONObject item = new JSONObject();
					item.put("Item", word.lemma);
					
					JSONArray mods = new JSONArray();
					
					for(int j = i-1; j >= 0; j--){
						WordProperties subWord = phrase.sentence.get(j);
						if(subWord.partOfSpeech.equalsIgnoreCase("ADJ") && subWord.parent.equals(word)){
							mods.put(subWord.lemma);
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
			object.put("Classifiers", jsonArray);
		}
		return false;
	}
	
	private boolean possibleMaterial(String testMaterial, JSONObject object){
		JSONArray jsonMats = (JSONArray) object.get("Build_With");
		ArrayList<String> mats = new ArrayList<String>();     
		if (jsonMats != null) { 
		   for (int i=0;i<jsonMats.length();i++){ 
			   mats.add(jsonMats.getString(i));
		   } 
		} 
		if(mats.size() > 0){
			for(String expectedMaterial: mats){
//				System.out.println(expectedMaterial + "#" + testMaterial);
				if(testMaterial.equalsIgnoreCase(expectedMaterial)){
					return true;
				}				
			}
			return false;
		}
		return true;
	}
		
	private boolean specifiesMaterial(SpokenPhrase phrase){
		for(int i = 0; i < phrase.sentence.size(); i ++){
			WordProperties word = phrase.sentence.get(i);
			for(String trigger: triggers){
				for(String triggerPeice: trigger.split(" ")){
					if(!triggerPeice.equalsIgnoreCase(word.lemma)){
						break;
					}
					return true;
				}
			}
		}
		return false;
	}

}
