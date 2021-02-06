package executables;
import java.util.ArrayList;
import java.util.List;

import Actions.BuildAction;
import Actions.NameAction;
import Actions.PickUpAction;
import Actions.PutDownAction;
import Actions.VerbalAction;
import actionArtifacts.CommandArtifact;
import actionArtifacts.UnknownArtifact;
import dataStructures.ConstructContainer;
import dataStructures.SpokenPhrase;
import dataStructures.SystemSavedConstructs;
import googleNLP.GoogleNLPTokenAPIRequest;
import googleNLP.NLPTokenParser;
import phraseParsers.BuildTypeParser;
import phraseParsers.FindBlockMods;
import phraseParsers.GestureParser;
import phraseParsers.NameParser;
import phraseParsers.PhraseParser;
import phraseParsers.WhatParser;
import phraseParsers.WhereParser;

public class Main {
	public static void main(String[] args) {
		String in = "Build a tower with only red and green blocks.";
		NLPTokenParser request = new GoogleNLPTokenAPIRequest();
		System.out.println(in);
		SpokenPhrase phrase = request.buildDependencyTree(in);
		List<VerbalAction> actions = new ArrayList<>();
		ConstructContainer container = new SystemSavedConstructs();
		PhraseParser nameParser = new NameParser();
		PhraseParser gestureParser = new GestureParser();
		PhraseParser findBlocksParser = new FindBlockMods();
		PhraseParser buildParser = new BuildTypeParser(container);
		PhraseParser whatParser = new WhatParser();
		PhraseParser whereParser = new WhereParser();
		
		actions.add(new NameAction(nameParser, gestureParser, findBlocksParser));
		actions.add(new PickUpAction(gestureParser, findBlocksParser));
		actions.add(new PutDownAction(gestureParser, findBlocksParser));
		actions.add(new BuildAction(whatParser, whereParser, buildParser));
		
		boolean found = false;
		for(VerbalAction action: actions){
			if(action.isAction(phrase)){
				System.out.println(action.parseImportant(phrase).getString());
				found = true;
				break;
			}
		}
		if(!found){
			System.out.println(new UnknownArtifact().getString());
		}
	}
}
