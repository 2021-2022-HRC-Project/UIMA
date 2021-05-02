package MemorySave;

public class NamedBlock {

	int id;
	String name;
	double x;
	double y;
	double z;


	public NamedBlock(int id, double x1, double y1, double z1, String name){
		this.id = id;
		this.x = x1;
		this.y = y1;
		this.z = z1;
		this.name = name;
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("This is the block " + name + "\n");
		out.append("X: " + x + "\n");
		out.append("Y: " + y + "\n");
		out.append("Z: " + z + "\n");
		
		return out.toString();
	}
}
