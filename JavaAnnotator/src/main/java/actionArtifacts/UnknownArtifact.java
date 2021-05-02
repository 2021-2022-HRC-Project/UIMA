package actionArtifacts;

import org.json.JSONObject;

import com.google.gson.Gson;

public class UnknownArtifact implements CommandArtifact {

	JSONObject compliedArtifact;
	
	public UnknownArtifact() {
		compliedArtifact = new JSONObject();
		compliedArtifact.put(COMMAND_TAG, "Unknown");
	}
	@Override
	public Gson generateOutput() {
		return null;
	}

	@Override
	public String getString() {
		return compliedArtifact.toString();
	}

}
