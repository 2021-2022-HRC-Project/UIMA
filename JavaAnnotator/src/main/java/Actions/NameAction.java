package Actions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import actionArtifacts.BlockArtifact;
import actionArtifacts.CommandArtifact;
import actionArtifacts.NameArtifact;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;
import phraseParsers.PhraseParser;

public class NameAction implements VerbalAction {

	public static final String ACTIONPHRASE = "name";
	public static final String ACTIONPARTOFSPEECH = "verb";
	
	PhraseParser nameParser;
	PhraseParser gestureParser;
	PhraseParser blockModParser;
	
	public NameAction(PhraseParser nameParser, PhraseParser gestureParser, PhraseParser blockModParser) {
		this.nameParser = nameParser;
		this.gestureParser = gestureParser;
		this.blockModParser = blockModParser;
	}
	
	@Override
	public boolean isAction(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(word.lemma.equalsIgnoreCase(ACTIONPHRASE) && word.partOfSpeech.equalsIgnoreCase(ACTIONPARTOFSPEECH)){
				return true;
			}
		}
		return false;
	}

	@Override
	public CommandArtifact parseImportant(SpokenPhrase phrase) {
		JSONObject object = new JSONObject();
		nameParser.findInformation(phrase, object);
		blockModParser.findInformation(phrase, object);
		gestureParser.findInformation(phrase, object);
		
		return new NameArtifact(object);
		
	}

}
