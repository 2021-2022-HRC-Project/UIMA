package annotatorServer;

import java.util.ArrayList;

import javax.swing.JTextArea;

import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;


public class SpeechResponseListener implements GSpeechResponseListener{
	String oldtext = "";
	ArrayList<String> stringList = new ArrayList<String>();
	private JTextArea response;
	String output = "";
	
	SpeechResponseListener(JTextArea response){
		this.response = response;
	}
	
	
	public void onResponse(GoogleResponse gr) {
		output = gr.getResponse();
		response.setText(oldtext+"\nprocessing: "+output);
		if(output.charAt(this.output.length()-1)=='.') {
			if(stringList.size()<1||!this.stringList.get(stringList.size()-1).equals(output)) {
				this.stringList.add(output);
				oldtext = oldtext+"\n"+output;
			}
		}
	}


	public String getRecorded() {
		return this.oldtext;
	}


	public void clearRecorded() {
		this.oldtext = "";
	}
	
}
