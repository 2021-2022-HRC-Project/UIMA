package googleNLP;

import dataStructures.SpokenPhrase;

public interface NLPTokenParser {
	SpokenPhrase buildDependencyTree(String toParse);
}
