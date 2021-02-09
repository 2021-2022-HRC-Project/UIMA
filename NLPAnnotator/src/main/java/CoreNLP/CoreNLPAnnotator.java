package CoreNLP;

import MemorySave.MemorySaveAnnotationType;
import MemorySave.NamedBlock;
import annotatorServer.Annotator;
import edu.stanford.nlp.pipeline.CoreSentence;
import CoreNLP.Models.ParseResultModel;
import CoreNLP.Workers.*;
import edu.stanford.nlp.pipeline.CoreSentence;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoreNLPAnnotator extends Annotator {
    private final static String warmUpText = "Pick up the blue block between this red block and the yellow block. ";
    private static Boolean firstTime = true;
    public String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.NLPProcessor\"";
    private InputAnnotator inputAnnotator;
    private SentenceParser sentenceParser;
    public CoreNLPAnnotator(){
        this.inputAnnotator = new InputAnnotator();
        this.sentenceParser = new SentenceParser();
    }

    private static void warmUp(InputAnnotator inputAnnotator, SentenceParser sentenceParser){
        String cleanedText = SentenceFilter.filter(warmUpText);
        List<CoreSentence> sentences = inputAnnotator.parse(cleanedText);

        for (CoreSentence sentence : sentences) {
            sentenceParser.parse(0, sentence);
        }
    }

    @Override
    public String process(String request) throws IOException {
        System.out.println("!!!!!!");
        JSONObject jsonObj = new JSONObject(request);

        String rawText = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("SpokenText").getJSONObject(0).getString("text");
        System.out.println(rawText);
        System.out.println(".......");
        if (firstTime) {
            warmUp(inputAnnotator, sentenceParser);
            firstTime = false;
        }
        System.out.println("??????");
        sentenceParser.resetPrevious();
        int seqNum = 0;
//        while (true){
//            boolean clarification = false;
//            System.out.println("Input message:");
//            String s = in.nextLine();
//            switch (s.toLowerCase()){
//                case "exit":
//                    return;
//                case "test":
//                    s = text;
//                    break;
//                case "clarify":
//                    clarification = true;
//                    System.out.println("Additional Info: ");
//                    s = in.nextLine();
//                default:
//            }
        System.out.println("debug msg 1");
        String cleanedText = SentenceFilter.filter(rawText);
        List<CoreSentence> sentences = inputAnnotator.parse(cleanedText);
        String result = "";
        System.out.println("debug msg 2");
        for (CoreSentence sentence : sentences) {
            System.out.println("debug msg 3");
            ParseResultModel tempResult = sentenceParser.parse(seqNum, sentence);
            System.out.println("debug msg 4");
            result = JSONResultWriter.writeResult(tempResult,seqNum);
            System.out.println("debug msg 5");
            seqNum++;
        }

//        }
        System.out.println(result);
        return result;
    }
}
