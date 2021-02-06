package SpatialRelationGenerator;
import java.util.PriorityQueue;



public class InnerBlock {
	
	int id;	
	String name;
	double x;
	double y;
	double z;
	

	public PriorityQueue<BlockWrapper> left;
	public PriorityQueue<BlockWrapper> right;
	public PriorityQueue<BlockWrapper> front;
	public PriorityQueue<BlockWrapper> behind;
	
	
	public InnerBlock(int id, double x1, double y1, double z1, String name){
		this.id = id;
		this.x = x1;
		this.y = y1;
		this.z = z1;
		this.name = name;
		
		this.left = new PriorityQueue<>();
		this.right = new PriorityQueue<>();
		this.front = new PriorityQueue<>();
		this.behind = new PriorityQueue<>();
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("This is the block " + name + "\n");
		out.append("\tLeft: "+ left.size());
		out.append("\tRight: " + right.size());
		out.append("\tFront: " + front.size());
		out.append("\tBehind: " + behind.size());
		
		return out.toString();
	}
}
