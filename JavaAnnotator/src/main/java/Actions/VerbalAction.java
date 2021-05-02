package Actions;

import actionArtifacts.CommandArtifact;
import dataStructures.SpokenPhrase;

public interface VerbalAction {
	boolean isAction(SpokenPhrase phrase);
	CommandArtifact parseImportant(SpokenPhrase phrase);
}
