package developerAnnotators;

import dataStructures.Annotator;

import java.io.IOException;

public class DeveloperSpeechToTextAnnotator extends Annotator {

    private final String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.SpokenText\":";
    private String resultString = "Pick up the blue block to the left of the red block";
//    private String resultString = "Pick up the blue block";



    @Override
    public String process(String request) throws IOException {
        String output = "{"+unitWrapper+"[{\"text\":"+resultString+"}]"+"}";
        return output;
    }
}
