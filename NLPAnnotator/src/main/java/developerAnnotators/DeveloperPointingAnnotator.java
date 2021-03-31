package developerAnnotators;

import dataStructures.Annotator;

import java.io.IOException;

public class DeveloperPointingAnnotator extends Annotator {
    private final String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.Pointing\":";

    @Override
    public String process(String request) throws IOException {
        String result = "[{'sofa':1,'begin':0,'end':0,'id':1.0,'confidence':0.7883012291917103},{'sofa':1,'begin':0,'end':0,'id':2.0,'confidence':0.4192664971408399},{'sofa':1,'begin':0,'end':0,'id':3.0,'confidence':0.7654227001515287},{'sofa':1,'begin':0,'end':0,'id':4.0,'confidence':0.9984557463704878},{'sofa':1,'begin':0,'end':0,'id':5.0,'confidence':-0.8508741601239602}]";
        return "{"+unitWrapper+result+"}";
    }
}
