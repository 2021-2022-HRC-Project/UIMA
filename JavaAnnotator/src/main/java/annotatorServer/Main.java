package annotatorServer;

import CoreNLP.CoreNLPAnnotator;
import Feedback.ConfidenceFeedbackAnnotator;
import MemoryLoad.MemoryLoadAnnotator;
import MemorySave.MemorySaveAnnotator;
import MetadataCompiler.MetadataAnnotator;
import SpatialRelationGenerator.SpatialRelationAnnotator;
import SpeechToText.SpeechToTextAnnotator;
import TextToSpeech.TextToSpeechAnnotator;
import dataStructures.Annotator;
import developerAnnotators.*;
import helloWorld.JavaHelloWorldAnnotator;
import planningUnit.PlanningAnnotator;

import static spark.Spark.port;
import static spark.Spark.post;

public class Main {

    public final static Boolean DEVELOPER_MODE = true;

    public static void main(String[] args) {

        port(3001);

        Annotator speech = DEVELOPER_MODE ? new DeveloperSpeechToTextAnnotator() : new SpeechToTextAnnotator();
        post("/Speech", speech);

        //ADD NEW TRUST ANNOTATOR

        Annotator metaData = new MetadataAnnotator();
        post("/MetadataCompiler", metaData);

        Annotator spatial = new SpatialRelationAnnotator();
        post("/SpatialRelationGen", spatial);

        Annotator handle = new CoreNLPAnnotator();
        post("/NLPUnit", handle);

        Annotator feedback = new ConfidenceFeedbackAnnotator();
        post("/Feedback", feedback);

//        Annotator textToSpeech = new TextToSpeechAnnotator();
//        post("/TextToSpeech", textToSpeech);

        Annotator memorySave = new MemorySaveAnnotator();
        post("/MemorySave", memorySave);

        Annotator memoryLoad = DEVELOPER_MODE ? new DeveloperMemoryLoadAnnotator() : new MemoryLoadAnnotator();
        post("/MemoryLoad", memoryLoad);

        Annotator planningUnit = DEVELOPER_MODE ? new DeveloperPlanningAnnotator() : new PlanningAnnotator();
        post("/Planning", planningUnit);

        Annotator helloWorld = new JavaHelloWorldAnnotator();
        post("/JavaHelloWorld", helloWorld);

        if (DEVELOPER_MODE){
			Annotator developerObjectDetection = new DeveloperObjectDetectionAnnotator();
			post("/DeveloperObjectDetection", developerObjectDetection);

			Annotator developerPointing = new DeveloperPointingAnnotator();
			post("/DeveloperPointing", developerPointing);
        }
    }
}