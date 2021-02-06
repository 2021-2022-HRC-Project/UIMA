package MemorySave;

import annotatorServer.AnnotationType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MemorySaveAnnotationType extends AnnotationType {
    private List<NamedBlock> namedBlocks = new ArrayList<>();

    public MemorySaveAnnotationType(String name, List<NamedBlock> blockList) {
        super(name);
        namedBlocks = blockList;
    }

    @Override
    public List<String> getFields() {
        List<String> output = new ArrayList<>();
        Gson gson = new Gson();
        output.add("{\"namedBlocks\":" + gson.toJson(this.namedBlocks) + "}");
        return output;
    }
}
