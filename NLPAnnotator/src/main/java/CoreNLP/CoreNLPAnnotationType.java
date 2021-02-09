package CoreNLP;

import annotatorServer.AnnotationType;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;

public class CoreNLPAnnotationType extends AnnotationType {
    private List<CoreMap> sentences;

    public CoreNLPAnnotationType(String name) {
        super(name);
    }

    @Override
    public List<String> getFields() {
        return null;
    }

    public List<CoreMap> getSentences(){
        return sentences;
    }
    public Integer getSize(){ return sentences.size(); }
}
