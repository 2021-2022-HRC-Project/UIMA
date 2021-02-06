package edu.rosehulman.aixprize.pipeline.core;

import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.*;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.json.JsonCasSerializer;
import org.apache.uima.resource.*;
import org.apache.uima.util.*;

public class Controller {

	public static void main(String[] args) throws InterruptedException {
		Log log = LogFactory.getLog(Controller.class);
		log.info("UIMA Version: " + UIMAFramework.getVersionString());

		//IntegratedPipelineAnnotatorDescriptor
		//BlockDetectionAnnotatorDescriptor
		//HelloWorld
		File compoundAnnotatorDescriptor = new File("desc/IntegratedPipelineAnnotatorDescriptor.xml");
		if (!compoundAnnotatorDescriptor.exists()) {
			log.fatal("Couldn't find descriptor at " + compoundAnnotatorDescriptor.getAbsolutePath());
		}
		try {
			XMLInputSource xmlInput = new XMLInputSource(compoundAnnotatorDescriptor);
			ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(xmlInput);

			Thread.sleep(4000);
			AnalysisEngine analysisEngine = UIMAFramework.produceAnalysisEngine(specifier);
			JCas cas = analysisEngine.newJCas();

			cas.setDocumentText("This is some document text. My face is blue and I am sad. red.");
			analysisEngine.process(cas);
			AnnotationIndex<Annotation> index = cas.getAnnotationIndex();
			index.forEach(annotation -> log.info("Found annotation: " + annotation));
			
			cas.reset();
		} catch (IOException e) {
			log.fatal("Failed to load descriptor.");
		} catch (InvalidXMLException e) {
			log.fatal("Invalid XML.");
		} catch (ResourceInitializationException e) {
			e.printStackTrace();
			log.fatal("Failed to initialize the analysis engine.");
		} catch (AnalysisEngineProcessException e) {
			log.fatal("Failed to process the analysis.");
		}
	}
}
