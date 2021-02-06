package Actions;

import org.json.JSONObject;

import actionArtifacts.CommandArtifact;
import actionArtifacts.PickUpArtifact;
import actionArtifacts.putdownArtifact;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;
import phraseParsers.PhraseParser;

public class PutDownAction implements VerbalAction{
	public final static String WORDONE = "Put";
	public final static String WORDTWO = "Down";
	public final static String PARTOFSPEECHONE = "VERB";
	public final static String PARTOFSPEECHTWO = "PRT";
	
	PhraseParser gestureParser;
	PhraseParser blockModParser;
	
	public PutDownAction(PhraseParser gestureParser, PhraseParser blockModParser) {
		this.gestureParser = gestureParser;
		this.blockModParser = blockModParser;
	}
	
	@Override
	public boolean isAction(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(word.lemma.equalsIgnoreCase(WORDTWO) && word.partOfSpeech.equalsIgnoreCase(PARTOFSPEECHTWO)){
				if(word.parent.lemma.equalsIgnoreCase(WORDONE) && word.parent.partOfSpeech.equalsIgnoreCase(PARTOFSPEECHONE)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public CommandArtifact parseImportant(SpokenPhrase phrase) {
		System.out.println("here");
		JSONObject object = new JSONObject();
		blockModParser.findInformation(phrase, object);
		gestureParser.findInformation(phrase, object);
		return new putdownArtifact(object);
	}
}
