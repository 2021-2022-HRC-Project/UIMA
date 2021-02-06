package MemoryLoad;

import MemorySave.NamedBlock;
import annotatorServer.AnnotationType;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MemoryLoadAnnotationType extends AnnotationType {
    private List<NamedBlock> namedBlocks = new ArrayList<>();

    public MemoryLoadAnnotationType(String name, List<NamedBlock> blockList) {
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
