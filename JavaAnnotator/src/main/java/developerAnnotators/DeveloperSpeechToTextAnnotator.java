package developerAnnotators;

import dataStructures.Annotator;

import java.io.IOException;

public class DeveloperSpeechToTextAnnotator extends Annotator {

    private final String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.SpokenText\":";
//    private String resultString = "Pick up the block to the right of the yellow block";
   private String resultString = "Pick up the blue block";
    // private String resultString = "Pick up the block between the yellow block and the purple block";
//    private String resultString = "Pick up the block between the red block and the blue block";



    @Override
    public String process(String request) throws IOException {
        String output = "{"+unitWrapper+"[{\"text\":"+resultString+"}]"+"}";
        return output;
    }
}
