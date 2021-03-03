package actionArtifacts;

import com.google.gson.Gson;

public interface CommandArtifact {
	
	String COMMAND_TAG = "command";
	String INFO_TAG = "info";
	Gson generateOutput();
	String getString();
	
}
