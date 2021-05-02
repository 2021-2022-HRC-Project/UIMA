package actionArtifacts;

import org.json.JSONObject;

import com.google.gson.Gson;

public class BuildArtifact implements CommandArtifact {

	
	private static final String COMMAND = "Build";
	
	private final JSONObject commandArtifact;
	
	public BuildArtifact(JSONObject object) {
		commandArtifact = new JSONObject();
		commandArtifact.put(COMMAND, object);
	}
	
	@Override
	public Gson generateOutput() {
		return null;
	}

	@Override
	public String getString() {
		return commandArtifact.toString();
	}

}
