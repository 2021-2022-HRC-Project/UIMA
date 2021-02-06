package MemoryLoad;

import MemorySave.MemorySaveAnnotationType;
import MemorySave.NamedBlock;
import MetadataCompiler.MetaBlock;
import annotatorServer.Annotator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MemoryLoadAnnotator extends Annotator {

    @Override
    public String process(String request) throws IOException {
        ArrayList<NamedBlock> blockList = getBlockList();

        MemorySaveAnnotationType annotation= new MemorySaveAnnotationType("\"edu.rosehulman.aixprize.pipeline.types.MemoryLoad\"",  blockList);
        String output = "{" + annotation.getName() + ": "+ annotation.getFields() + "}";
        return output;
    }

    private ArrayList<NamedBlock> getBlockList() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("MemorySave.txt"));
        String json = "";
        String line;
        while((line = reader.readLine()) != null) {
            json +=line;
        }


        JSONObject jsonObj = new JSONObject(json);

        JSONArray NLPJsonArray = jsonObj.getJSONArray("edu.rosehulman.aixprize.pipeline.types.MemorySave").getJSONObject(0).getJSONArray("namedBlocks");
        ArrayList<NamedBlock> blockList = new ArrayList<>();
        for(int i = 0; i < NLPJsonArray.length(); i++){
            JSONObject block = NLPJsonArray.getJSONObject(i);

            NamedBlock namedBlock = new NamedBlock(block.getInt("id"),
                    block.getDouble("x"),
                    block.getDouble("y"),
                    block.getDouble("z"),
                    block.getString("name"));

            blockList.add(namedBlock);
        }
        return blockList;
    }

}
