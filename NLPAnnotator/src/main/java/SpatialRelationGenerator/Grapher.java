package SpatialRelationGenerator;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import annotatorServer.Annotator;

public class Grapher {
	
	final double MAX_DISTANCE = 1.50;
	double WIDTH_OF_WORKING_SPACE;
	
	List<InnerBlock> blocks;
	
	public Grapher (List<InnerBlock> blocks, double workingSpaceWidth){
		this.blocks = blocks;
		this.WIDTH_OF_WORKING_SPACE = workingSpaceWidth;
	}
	
	public void makeGraph (){
		for(int i = 0; i < blocks.size(); i++){
			InnerBlock current = blocks.get(i);
			for(int j = i + 1; j < blocks.size(); j++){
				checkRelation(current, blocks.get(j));
			}
		}
	}
	
	public String generateAnnotationResponse() throws JsonProcessingException{
		String output = null;
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode jsonData = mapper.createArrayNode();
		for(InnerBlock b: blocks){
			ObjectNode block = mapper.createObjectNode();
			block.put("Id", b.id);
			block.put("Name" , b.name);
			block.put("X", b.x);
			block.put("Y", b.y);
			block.put("Z", b.z);
			addListsToObjectNode(block, b, mapper);
			jsonData.add(block);
		}

		output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData);
		return output;
	}
	
	public void addListsToObjectNode(ObjectNode block, InnerBlock b, ObjectMapper mapper){
		ArrayNode left = mapper.createArrayNode();
		ArrayNode right = mapper.createArrayNode();
		ArrayNode front = mapper.createArrayNode();
		ArrayNode behind = mapper.createArrayNode();
		
		for(BlockWrapper innerBlock: b.behind){
			ObjectNode BID = mapper.createObjectNode();
			BID.put("id", innerBlock.block.id);
			behind.add(BID);
		}
		for(BlockWrapper innerBlock: b.front){
			ObjectNode BID = mapper.createObjectNode();
			BID.put("id", innerBlock.block.id);
			front.add(BID);	
		}
		for(BlockWrapper innerBlock: b.left){
			ObjectNode BID = mapper.createObjectNode();
			BID.put("id", innerBlock.block.id);
			left.add(BID);
		}
		for(BlockWrapper innerBlock: b.right){
			ObjectNode BID = mapper.createObjectNode();
			BID.put("id", innerBlock.block.id);
			right.add(BID);
		}
		
		block.putPOJO("left", left);
		block.putPOJO("right", right);
		block.putPOJO("front", front);
		block.putPOJO("behind", behind);
	}

	
	private void checkRelation(InnerBlock current, InnerBlock other) {
		//ad in relations to check against
		ArrayList<SpatialRelation> relations = new ArrayList<>();
		relations.add(new Left());
		relations.add(new Right());
		relations.add(new Front());
		relations.add(new Behind());
		
		Vector3D vector = new Vector3D(other.x-current.x, other.y-current.y, other.z - current.z);
		SphericalCoordinates po = new SphericalCoordinates(vector);
		
		
//		//PolarCoordinates po = new PolarCoordinates(current.x, current.y, current.z, other.x, other.y, other.z);
//		System.out.println("Current: " + current.name + " other: " + other.name+ "\n\tphi: " + po.getPhi() + " theta: " + po.getTheta());
		
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
		
//		else if (po.getTheta() < Math.PI/2) {
//			if(po.getPhi() < Math.PI/4){
//				current.front.add(otherWrapper);
//				other.behind.add(currentWrapper);
//			}else if (po.getPhi() <= (3.0*Math.PI)/4.0){
//				current.right.add(otherWrapper);
//				other.left.add(currentWrapper);
//			}else{
//				current.behind.add(otherWrapper);
//				other.front.add(currentWrapper);
//			}
//		}else{
//			if(po.getPhi() < Math.PI/4){
//				current.behind.add(otherWrapper);
//				other.front.add(currentWrapper);
//			}else if (po.getPhi() <= (3.0*Math.PI)/4.0){
//				current.left.add(otherWrapper);
//				other.right.add(currentWrapper);
//			}else{
//				current.front.add(otherWrapper);
//				other.behind.add(currentWrapper);
//			}
//		}
	}
	
	public double getConfidenceValue(double distance) {
		
		double x  = distance/(this.WIDTH_OF_WORKING_SPACE/2.0);
		
		return 1/(1+Math.pow(Math.E,(x-5)));
	}
}
