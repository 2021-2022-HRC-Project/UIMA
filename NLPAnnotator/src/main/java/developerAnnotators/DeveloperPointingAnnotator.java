package developerAnnotators;

import dataStructures.Annotator;

import java.io.IOException;

public class DeveloperPointingAnnotator extends Annotator {
    private final String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.Pointing\":";

    @Override
    public String process(String request) throws IOException {
        String result = "[{'id':1.0,'confidence':0.7083620230684211},{'id':2.0,'confidence':0.24950724697827417},{'id':3.0,'confidence':0.08218268979262786},{'id':4.0,'confidence':-0.07900363845721572},{'id':5.0,'confidence':-0.5846593124985601},{'id':6.0,'confidence':0.06710809200111145},{'id':7.0,'confidence':-0.022121716926781342},{'id':8.0,'confidence':-0.9156020968540153},{'id':9.0,'confidence':-0.5315046072402078},{'id':10.0,'confidence':-0.8471382144358836},{'id':11.0,'confidence':-0.6316842563953068}]";
        return "{"+unitWrapper+result+"}";
    }
}
