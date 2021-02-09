package SpeechToText;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.StreamingRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;

import java.util.ArrayList;

public class SpeechToTextResponseObserver implements ResponseObserver<StreamingRecognizeResponse> {
    ArrayList<StreamingRecognizeResponse> responses = new ArrayList<>();
    private String resultString;

    public void onStart(StreamController controller) {}

    public void onResponse(StreamingRecognizeResponse response) {
        responses.add(response);
    }

    public void onComplete() {
        for (StreamingRecognizeResponse response : responses) {
            StreamingRecognitionResult result = response.getResultsList().get(0);
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
            resultString = alternative.getTranscript();
            System.out.printf("Transcript : %s\n", alternative.getTranscript());
        }
    }

    public void onError(Throwable t) {
        System.out.println(t);
    }

    public String getResult(){
        return resultString;
    }
}
