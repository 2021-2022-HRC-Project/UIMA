package SpatialRelationGenerator;

import java.util.ArrayList;

import org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class test {
	
	final static double MAX_DISTANCE = 1.50;
	static double WIDTH_OF_WORKING_SPACE = 2;

	public static void main(String args[]){
//		InnerBlock current = new InnerBlock(1, -0.2783946692943573, -0.21063174307346344, 0.9890000224113464, "1");
//		InnerBlock other = new InnerBlock(4, -0.9101457595825195, 0.21746917068958282, 1.7950000762939453, "4");
		
		InnerBlock other = new InnerBlock(1, -0.2783946692943573, -0.21063174307346344, 0.9890000224113464, "1");
		InnerBlock current = new InnerBlock(4, -0.9101457595825195, 0.21746917068958282, 1.7950000762939453, "4");
		
		Vector3D vector = new Vector3D(other.x-current.x, other.y-current.y, other.z-current.z);
		SphericalCoordinates po = new SphericalCoordinates(vector);
		
		ArrayList<SpatialRelation> relations = new ArrayList<>();
		relations.add(new Left());
		relations.add(new Right());
		relations.add(new Front());
		relations.add(new Behind());
		
		double distance = Math.abs(po.getR());
		
		double confidence = getConfidenceValue(distance);
		
		BlockWrapper otherWrapper = new BlockWrapper(other, distance,confidence);
		BlockWrapper currentWrapper = new BlockWrapper(current, distance, confidence);
		
		if (distance > MAX_DISTANCE){
			System.out.println("out of range");
		} else {
			for(SpatialRelation relation : relations) {
				relation.checkRelation(po, currentWrapper, otherWrapper, current, other);
			}
		}
		
		System.out.println("Current:");
		System.out.println(currentWrapper.block);
		System.out.println("Other:");
		System.out.println(otherWrapper.block);
	}
	
	public static double getConfidenceValue(double distance) {
		
		double x  = distance/(WIDTH_OF_WORKING_SPACE/2.0);
		
		return 1/(1+Math.pow(Math.E,(x-5)));
	}
}
