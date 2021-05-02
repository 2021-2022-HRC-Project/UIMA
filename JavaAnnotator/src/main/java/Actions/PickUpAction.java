package Actions;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import actionArtifacts.CommandArtifact;
import actionArtifacts.PickUpArtifact;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;
import phraseParsers.PhraseParser;

public class PickUpAction implements VerbalAction {

	public final static String WORDONE = "Pick";
	public final static String WORDTWO = "UP";
	public final static String PARTOFSPEECHONE = "VERB";
	public final static String PARTOFSPEECHTWO = "PRT";
	
	PhraseParser gestureParser;
	PhraseParser blockModParser;
	
	public PickUpAction(PhraseParser gestureParser, PhraseParser blockModParser) {
		this.gestureParser = gestureParser;
		this.blockModParser = blockModParser;
	}
	
	@Override
	public boolean isAction(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(word.lemma.equalsIgnoreCase(WORDTWO) && word.partOfSpeech.equalsIgnoreCase(PARTOFSPEECHTWO)){
				if(word.parent.lemma.equalsIgnoreCase(WORDONE) && word.parent.partOfSpeech.equalsIgnoreCase(PARTOFSPEECHONE)){
					System.out.println("here");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public CommandArtifact parseImportant(SpokenPhrase phrase) {
		JSONObject object = new JSONObject();
		blockModParser.findInformation(phrase, object);
		gestureParser.findInformation(phrase, object);
		return new PickUpArtifact(object);
	}

}
