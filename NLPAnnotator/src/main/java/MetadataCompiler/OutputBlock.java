package MetadataCompiler;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;



public class OutputBlock {
	
	int id;	
	String name;
	double x;
	double y;
	double z;
	double confidenceValue;
	
	public String left;
	public String right;
	public String front;
	public String behind;
	
	public OutputBlock(MetaBlock block,double confidenceValue){
		this.id = block.id;
		this.name = block.name;
		this.x = block.x;
		this.y = block.y;
		this.z = block.z;
		this.confidenceValue = confidenceValue;
		this.left = toStringOfIDs(block.left);
		this.right = toStringOfIDs(block.right);
		this.behind = toStringOfIDs(block.behind);
		this.front = toStringOfIDs(block.front);
	}
	
	
	public OutputBlock(int id, double x1, double y1, double z1, String name){
		this.id = id;
		this.x = x1;
		this.y = y1;
		this.z = z1;
		this.name = name;
		
		this.left = "";
		this.right = "";
		this.front = "";
		this.behind = "";
	}
	
	public String toStringOfIDs (List<MetablockConfidenceTuple> blocks){
		List<Integer> output = new ArrayList<>();
		for(MetablockConfidenceTuple b : blocks){
			output.add(b.metaBlock.id);
		}
		return output.toString();	
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("This is the block " + name + "\n");
		out.append("\tLeft: "+ left);
		out.append("\tRight: " + right);
		out.append("\tFront: " + front);
		out.append("\tBehind: " + behind);
		
		return out.toString();
	}
}
