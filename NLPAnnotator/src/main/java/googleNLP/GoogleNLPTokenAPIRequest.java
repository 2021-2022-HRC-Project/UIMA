package googleNLP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;

import dataStructures.SpokenPhrase;
import dataStructures.WordProperties;

import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Token;

public class GoogleNLPTokenAPIRequest implements NLPTokenParser{

	private LanguageServiceClient language;
	
	public GoogleNLPTokenAPIRequest() {
		this.language = generateLanguage();
	}
	
	@Override
	public SpokenPhrase buildDependencyTree(String input) {
		AnalyzeSyntaxResponse response = sendAPIRequest(input);
		SpokenPhrase phrase = parseToSpokenPhrase(response);
		return phrase;
	}
	
	private SpokenPhrase parseToSpokenPhrase(AnalyzeSyntaxResponse response) {
		List<WordProperties> list = new ArrayList<>();
		for(int i = 0; i < response.getTokensList().size(); i++){
			Token token = response.getTokensList().get(i);
			String lemma = token.getLemma();
			String partOfSpeech = token.getPartOfSpeech().getTag().name();
			String dependencyEdge = token.getDependencyEdge().getLabel().name();
			
			boolean isProper = token.getPartOfSpeech().getProper().toString().equals("PROPER");
			boolean isSingular = token.getPartOfSpeech().getNumber().toString().equals("SINGULAR");
			WordProperties properties = new WordProperties(lemma, partOfSpeech, dependencyEdge, isProper, isSingular);
			list.add(properties);
		}
		for(int i = 0; i < response.getTokensList().size(); i++){
			Token token = response.getTokensList().get(i);
			int parent = token.getDependencyEdge().getHeadTokenIndex();
			list.get(i).parent = list.get(parent);
		}
		return new SpokenPhrase(list);
	}

	private AnalyzeSyntaxResponse sendAPIRequest(String input){
		Document doc = getTextAsDocument(input);
		AnalyzeSyntaxRequest request = addDoc(doc);
		return language.analyzeSyntax(request);
	}
	
	private AnalyzeSyntaxRequest addDoc(Document doc){
		return AnalyzeSyntaxRequest.newBuilder()
				.setDocument(doc)
				.setEncodingType(EncodingType.UTF16)
				.build();
	}
	
	private Document getTextAsDocument(String input){
		return Document.newBuilder()
			      .setContent(input)
			      .setType(Type.PLAIN_TEXT)
			      .build();
	}
	
	private LanguageServiceClient generateLanguage(){
		LanguageServiceClient generatedLanguage = null;
		try {
			generatedLanguage = LanguageServiceClient.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return generatedLanguage;
	}

	
	
	
}
