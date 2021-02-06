package annotatorServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Annotator implements Route{
	
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		System.out.println(request.body());
		String jsonOutput = process(request.body());
		setHeader(response);
		sendResponse(response, jsonOutput);
		return response.body();
	}
	
	public abstract String process(String request) throws IOException;
	
	public void setHeader(Response response){
		response.status(200);
		response.header("Content-Type", "application/json; charset=\"utf-8\"");
	}
	
	public void sendResponse(Response response, String data){
		response.body(data);
	}
	

}
