package dataStructures;

public class WordProperties {
	
	public String lemma;
	public String partOfSpeech;
	public String dependencyEdge;
	public WordProperties parent; 
	public boolean isProper;
	public boolean isSingular;
	
	public WordProperties(String lemma, String partOfSpeech, String dependencyEdge, boolean isProper, boolean isSingular) {
		this.lemma = lemma;
		this.partOfSpeech = partOfSpeech;
		this.dependencyEdge = dependencyEdge;
		this.parent = null;
		this.isProper = isProper;
		this.isSingular = isSingular;
	}
}
