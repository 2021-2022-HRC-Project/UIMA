package dataStructures;

import java.util.ArrayList;
import java.util.List;

public abstract class ConstructContainer {

	protected List<Construct> allowedConstructs;
	
	public ConstructContainer() {
		allowedConstructs = new ArrayList<>();
	}
	
	public Construct findMatching(String objectToBuild){
		for(Construct construct: allowedConstructs){
			if(construct.aliases.contains(objectToBuild.toLowerCase())){
				return construct;
			}
		}
		return null;
	}
	
}
