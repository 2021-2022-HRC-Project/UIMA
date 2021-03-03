package templateRoute;

import com.google.gson.Gson;
import dataStructures.Annotator;

public class TestRoute extends Annotator {

	Template temp;
	public TestRoute() {
		this.temp = new Template();
	}
	
	@Override
	public String process(String request) {
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(temp));
		return gson.toJson(temp);
	}

}
