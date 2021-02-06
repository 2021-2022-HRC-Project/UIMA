package annotatorServer;

import java.util.List;

public abstract class AnnotationType{

	private String name;
	public AnnotationType(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public abstract List<String> getFields();
	
}
