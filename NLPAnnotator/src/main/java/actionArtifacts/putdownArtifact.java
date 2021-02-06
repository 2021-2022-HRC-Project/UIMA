package actionArtifacts;

import org.json.JSONObject;

import com.google.gson.Gson;

public class putdownArtifact implements CommandArtifact{
	
	
	
	private static final String COMMAND = "put down";
	JSONObject compiledArtifact;
	
	public putdownArtifact(JSONObject object) {
		compiledArtifact = new JSONObject();
		compiledArtifact.put(COMMAND_TAG, COMMAND);
		compiledArtifact.put(INFO_TAG, object); 
	}
	
	@Override
	public Gson generateOutput() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getString() {
		return compiledArtifact.toString();
	}
	
}
