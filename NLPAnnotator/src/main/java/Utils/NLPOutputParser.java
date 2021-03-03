package Utils;

public class NLPOutputParser {
    public static String findValueOf(String outputData, String property){
        int propertyIdx = outputData.indexOf(property);
        String propertySubString = outputData.substring(propertyIdx);
        propertySubString = propertySubString.substring(propertySubString.indexOf(":") + 1, propertySubString.indexOf(",") - 1);
        String propertyString = propertySubString.replaceAll("\"", "");
        return propertyString;
    }
}
