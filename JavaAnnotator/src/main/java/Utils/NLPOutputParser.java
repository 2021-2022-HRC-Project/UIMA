package Utils;

import CoreNLP.Models.ParseResultModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;


public class NLPOutputParser {
    public final static String NLP_PROCESSOR_STRING = "edu.rosehulman.aixprize.pipeline.types.NLPProcessor";
    public static String findValueOf(String outputData, String property){
        int propertyIdx = outputData.indexOf(property);
        String propertySubString = outputData.substring(propertyIdx);
        propertySubString = propertySubString.substring(propertySubString.indexOf(":") + 1, propertySubString.indexOf(",") - 1);
        String propertyString = propertySubString.replaceAll("\"", "");
        return propertyString;
    }

    public static ParseResultModel readJsonToModel(int seqNum)  {
        String resourceName = String.format("./JSONOutput/outputJson%d.json", seqNum);
        System.err.println(resourceName);
        Gson gson = new Gson();

        JsonReader reader = null;
        System.out.println("!!!!!!!!!!!!!!!");
        try {
            reader = new JsonReader(new FileReader(resourceName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("?????????????");
        Type fileType = new TypeToken<Map<String, ParseResultModel>>() {}.getType();
        Map<String, ParseResultModel> result = gson.fromJson(reader,fileType);
        System.err.println(result.get(NLP_PROCESSOR_STRING).getCommand());
        return result.get(NLP_PROCESSOR_STRING);
    }
}
