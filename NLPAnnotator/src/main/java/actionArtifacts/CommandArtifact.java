package actionArtifacts;

import com.google.gson.Gson;

public interface CommandArtifact {
	
	static final String COMMAND_TAG = "command";
	static final String INFO_TAG = "info";
	public Gson generateOutput();
	public String getString();
	
}
