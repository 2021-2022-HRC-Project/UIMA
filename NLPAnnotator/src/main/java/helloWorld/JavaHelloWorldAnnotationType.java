package helloWorld;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import annotatorServer.AnnotationType;

public class JavaHelloWorldAnnotationType extends AnnotationType{
	
	public String text;

	public JavaHelloWorldAnnotationType(String name, String text) {
		super(name);
		this.text = text;
	}

	@Override
	public List<String> getFields() {
		ArrayList<String> output = new ArrayList<>();
		output.add("{\"text\": " + this.text + "}");
		return output;
	}

}
