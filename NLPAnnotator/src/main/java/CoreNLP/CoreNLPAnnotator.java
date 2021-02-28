package CoreNLP;

import annotatorServer.Annotator;
import edu.stanford.nlp.pipeline.CoreSentence;
import CoreNLP.Models.ParseResultModel;
import CoreNLP.Workers.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class CoreNLPAnnotator extends Annotator {
    private final static String warmUpText = "Pick up the blue block between this red block and the yellow block. ";
    private static Boolean firstTime = true;
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
        JSONObject jsonObj = new JSONObject(request);

        String rawText = jsonObj.getJSONObject("_views").getJSONObject("_InitialView").getJSONArray("SpokenText").getJSONObject(0).getString("text").toLowerCase();
        System.out.println(rawText);
        if (firstTime) {
            warmUp(inputAnnotator, sentenceParser);
            firstTime = false;
        }
        sentenceParser.resetPrevious();
        int seqNum = 0;
        String cleanedText = SentenceFilter.filter(rawText);
        List<CoreSentence> sentences = inputAnnotator.parse(cleanedText);
        String result = "";
        for (CoreSentence sentence : sentences) {
            ParseResultModel tempResult = sentenceParser.parse(seqNum, sentence);
            result = JSONResultWriter.writeResult(tempResult,seqNum);
            seqNum++;
        }

        return result;
    }
}
