package Factories;

import java.util.ArrayList;
import java.util.List;

import Actions.BuildAction;
import Actions.NameAction;
import Actions.PickUpAction;
import Actions.PutDownAction;
import Actions.VerbalAction;
import annotatorServer.NLPAnnotatorUnit;
import dataStructures.ConstructContainer;
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

public class NLPAnnotatorFactory {
	
	public NLPAnnotatorUnit createNLPAnnotator(){			
		//define the verbal actions accepted by the nlp Unit
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
		
		//define the parser the nlp unit will use
		NLPTokenParser parser = new GoogleNLPTokenAPIRequest();
		
		//create the NLP Annotator
		return new NLPAnnotatorUnit(parser, actions);
	}
}
