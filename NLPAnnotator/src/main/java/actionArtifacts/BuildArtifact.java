package actionArtifacts;

import org.json.JSONObject;

import com.google.gson.Gson;

public class BuildArtifact implements CommandArtifact {

	
	private static final String COMMAND = "Build";
	
	private JSONObject commandArtifact;
	
	public BuildArtifact(JSONObject object) {
		commandArtifact = new JSONObject();
		commandArtifact.put(COMMAND, object);
	}
	
	@Override
	public Gson generateOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return commandArtifact.toString();
	}

}
