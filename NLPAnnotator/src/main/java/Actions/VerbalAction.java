package Actions;

import actionArtifacts.CommandArtifact;
import dataStructures.SpokenPhrase;

public interface VerbalAction {
	public boolean isAction(SpokenPhrase phrase);
	public CommandArtifact parseImportant(SpokenPhrase phrase); 
}
