package SpatialRelationGenerator;

import java.util.PriorityQueue;

public class InnerBlockBuilder {
	
	int id;
	String name;
	double x;
	double y;
	double z;
	
	public InnerBlockBuilder withId(int id) {
		this.id = id;
		return this;
	}
	
	public InnerBlockBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public InnerBlockBuilder withX(double x) {
		this.x = x;
		return this;
	}
	
	public InnerBlockBuilder withY(double y) {
		this.y = y;
		return this;
	}
	
	public InnerBlockBuilder withZ(double z) {
		this.z = z;
		return this;
	}
	
	public InnerBlock build(){
		return new InnerBlock(id, x, y, z, name);
	}
	
}
