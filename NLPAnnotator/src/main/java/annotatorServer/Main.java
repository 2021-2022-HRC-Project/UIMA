package annotatorServer;
import static spark.Spark.*;

import MemoryLoad.MemoryLoadAnnotator;
import MemorySave.MemorySaveAnnotator;
import com.google.gson.Gson;

import Factories.NLPAnnotatorFactory;
import Feedback.ConfidenceFeedbackAnnotator;
import MetadataCompiler.MetadataAnnotator;
import SpatialRelationGenerator.SpatialRelationAnnotator;
import TextToSpeech.TextToSpeechAnnotator;
import helloWorld.JavaHelloWorldAnnotator;

public class Main {

	public static void main(String[] args) {

		NLPAnnotatorFactory NLPFactory = new NLPAnnotatorFactory();
		
		port(3001);
		
		Annotator speech = new SpeechToTextAnnotator();
		post("/Speech", speech);
		Annotator metaData = new MetadataAnnotator();
		post("/MetadataCompiler", metaData);
		Annotator spatial = new SpatialRelationAnnotator();
		post("/SpatialRelationGen", spatial);
		Annotator handle = NLPFactory.createNLPAnnotator();
		post("/NLPUnit", handle);
		Annotator feedback = new ConfidenceFeedbackAnnotator();
		post("/Feedback", feedback);
		Annotator textToSpeech = new TextToSpeechAnnotator();
		post("/TextToSpeech", textToSpeech);
		
		Annotator memorySave = new MemorySaveAnnotator();
		post("/MemorySave", memorySave);
		Annotator memoryLoad = new MemoryLoadAnnotator();
		post("/MemoryLoad", memoryLoad);
		
		Annotator helloWorld = new JavaHelloWorldAnnotator();
		post("/JavaHelloWorld", helloWorld);
	}
}