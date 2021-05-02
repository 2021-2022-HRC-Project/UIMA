package CoreNLP.Models;

import edu.stanford.nlp.util.CoreMap;

import java.util.List;

public class AnnotationParseResult{
    private final List<CoreMap> sentences;

    public AnnotationParseResult(List<CoreMap> sentences){
        this.sentences = sentences;
    }

    public List<CoreMap> getSentences(){
        return sentences;
    }
    public Integer getSize(){ return sentences.size(); }
}
