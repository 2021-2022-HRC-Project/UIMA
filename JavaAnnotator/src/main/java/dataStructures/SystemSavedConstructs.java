package dataStructures;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemSavedConstructs extends ConstructContainer implements ConstructLoader {
		
	public SystemSavedConstructs() {
		super();
		loadConstructs();
	}
	
	
	public  void loadConstructs(){
		Construct towerConstruct =  new Construct("Tower", Arrays.asList("tower", "stack"), Arrays.asList("block"));
		Construct lineConstruct =  new Construct("Line", Arrays.asList("line", "bridge"), Arrays.asList("block"));
		allowedConstructs.add(towerConstruct);
		allowedConstructs.add(lineConstruct);
	}
}
