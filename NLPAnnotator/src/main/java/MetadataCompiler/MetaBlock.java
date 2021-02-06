package MetadataCompiler;

import java.util.LinkedList;
import java.util.PriorityQueue;

import SpatialRelationGenerator.BlockWrapper;

public class MetaBlock {
	
	int id;	
	String name;
	double x;
	double y;
	double z;
	

	public LinkedList<MetablockConfidenceTuple> left;
	public LinkedList<MetablockConfidenceTuple> right;
	public LinkedList<MetablockConfidenceTuple> front;
	public LinkedList<MetablockConfidenceTuple> behind;
	
	
	public MetaBlock(int id, double x1, double y1, double z1, String name){
		this.id = id;
		this.x = x1;
		this.y = y1;
		this.z = z1;
		this.name = name;
		
		this.left = new LinkedList<>();
		this.right = new LinkedList<>();
		this.front = new LinkedList<>();
		this.behind = new LinkedList<>();
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("This is the block " + name + "\n");
		out.append("X: " + x + "\n");
		out.append("Y: " + y + "\n");
		out.append("Z: " + z + "\n");
		out.append("\tLeft: "+ left.size());
		out.append("\tRight: " + right.size());
		out.append("\tFront: " + front.size());
		out.append("\tBehind: " + behind.size());
		
		return out.toString();
	}
}
