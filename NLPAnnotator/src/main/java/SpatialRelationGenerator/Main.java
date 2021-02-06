package SpatialRelationGenerator;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import MetadataCompiler.MetaBlock;
import MetadataCompiler.MetadataCompiler;
import MetadataCompiler.OutputBlock;

public class Main {
	
	
	
	

	public static void main(String[] args) throws JsonProcessingException{
		
		InnerBlock origin = new InnerBlock(1,0,0,1.5, "origin");
		
		InnerBlock front = new InnerBlock(3,0.3,0,2.70, "front");
		
		InnerBlock left = new InnerBlock(5,-0.98,0,1.69, "left");
		
		InnerBlock right = new InnerBlock(4,1.20,0,1.30, "right");
		
		InnerBlock behind = new InnerBlock(2,0.30,0,1.10, "behind");
		
		InnerBlock faraway = new InnerBlock(6,-3.00,0,1.25, "faraway");
		
		List<InnerBlock> blocks  = new ArrayList<>();
		
		blocks.add(origin);
		blocks.add(front);
		blocks.add(behind);
		blocks.add(right);
		blocks.add(left);
		blocks.add(faraway);
		
		Grapher grapher = new Grapher(blocks,2);
		
		grapher.makeGraph();
		
		for(InnerBlock b: blocks){
			System.out.println(b);
		}
		
		System.out.println("\n\n\nYEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEET\n\n\n\n");
		
		System.out.println(grapher.generateAnnotationResponse());

	}
}
