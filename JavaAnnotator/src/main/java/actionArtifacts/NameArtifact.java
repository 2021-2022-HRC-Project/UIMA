package actionArtifacts;

import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

public class NameArtifact implements CommandArtifact {

	private static final String COMMAND = "Name";
	JSONObject compiledArtifact;
	
	public NameArtifact(JSONObject object) {
		compiledArtifact = new JSONObject();
		compiledArtifact.put(COMMAND_TAG, COMMAND);
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
