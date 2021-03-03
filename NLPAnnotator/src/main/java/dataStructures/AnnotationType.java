package dataStructures;

import java.util.List;

public abstract class AnnotationType{

	private final String name;
	public AnnotationType(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract List<String> getFields();
	
}
