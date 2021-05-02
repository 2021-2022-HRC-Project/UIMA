package actionArtifacts;

import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

public class PickUpArtifact implements CommandArtifact {

	private static final String COMMAND = "pick up";
	JSONObject compiledArtifact;
	
	public PickUpArtifact(JSONObject object) {
		compiledArtifact = new JSONObject();
		compiledArtifact.put("Command", COMMAND);
		compiledArtifact.put(INFO_TAG, object);
	}
	
	@Override
	public Gson generateOutput() {
		return null;
	}
	@Override
	public String getString() {
		return compiledArtifact.toString();
	}

}
