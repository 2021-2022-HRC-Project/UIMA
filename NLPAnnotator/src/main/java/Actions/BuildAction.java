package Actions;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import actionArtifacts.BuildArtifact;
import actionArtifacts.CommandArtifact;
import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;
import phraseParsers.PhraseParser;

public class BuildAction implements VerbalAction {
	
	private static final List<String> TRIGGER_WORD_LIST = Arrays.asList("build", "construct");
	private static final String TRIGGER_PART_OF_SPEECH = "verb";

	private PhraseParser buildWith;
	private PhraseParser locationParser;
	private PhraseParser buildTypeParser;
	
	public BuildAction(PhraseParser buildWith, PhraseParser locationParser, PhraseParser buildTypeParser) {
		this.buildWith = buildWith;
		this.locationParser = locationParser;
		this.buildTypeParser = buildTypeParser;
	}
	
	
	@Override
	public boolean isAction(SpokenPhrase phrase) {
		for(WordProperties word: phrase.sentence){
			if(TRIGGER_WORD_LIST.contains(word.lemma.toLowerCase()) && word.partOfSpeech.equalsIgnoreCase(TRIGGER_PART_OF_SPEECH)){
				return true;
			}
		}
		return false;
	}

	@Override
	public CommandArtifact parseImportant(SpokenPhrase phrase) {
		// TODO Auto-generated method stub
		JSONObject object = new JSONObject();
		buildTypeParser.findInformation(phrase, object);
		buildWith.findInformation(phrase, object);
		locationParser.findInformation(phrase, object);
		return new BuildArtifact(object);
	}

	
}
