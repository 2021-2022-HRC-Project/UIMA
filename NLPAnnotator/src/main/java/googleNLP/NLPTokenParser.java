package googleNLP;

import dataStructures.SpokenPhrase;

public interface NLPTokenParser {
	public SpokenPhrase buildDependencyTree(String toParse);
}
