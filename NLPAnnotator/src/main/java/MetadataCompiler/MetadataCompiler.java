package MetadataCompiler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class MetadataCompiler {
	
	public Stack<Double> pathConfidenceValues = new Stack<Double>();
	
	public double getPathConfidenceValue() {
		double total = 1;
		
		for(Double value : this.pathConfidenceValues) {
			total = total * value;
		}
		
		return total;
	}
	
	public MetaBlock chooseBlock(List<String> relationKeywords, List<Integer> degrees, MetaBlock startBlock){
		MetaBlock block = null;
		
		//make queue with initial starting block
		Queue<MetablockConfidenceTuple> initialBlockQueue = new LinkedList<>();
		initialBlockQueue.add(new MetablockConfidenceTuple(startBlock,1));

		
		//index is the number of relation keywords gone through thusfar
		int index = 0;
		Queue<MetablockConfidenceTuple> returnedFinalList = recursivlySearchForBlock(initialBlockQueue,relationKeywords,degrees,index);
		
		if(returnedFinalList == null){
			//nothing found
		}
		else
		{
			block = returnedFinalList.poll().metaBlock;
		}
		
		return block;
	}
	
	private Queue<MetablockConfidenceTuple> recursivlySearchForBlock(Queue<MetablockConfidenceTuple> initialBlockQueue, List<String> relationKeywords, List<Integer> degrees, int index) {
		Queue<MetablockConfidenceTuple> blocks = null;
		
		if(index == relationKeywords.size())
		{
			return initialBlockQueue;
		}
		
		while(!initialBlockQueue.isEmpty() && blocks == null) 
		{
			MetablockConfidenceTuple currentBlock = initialBlockQueue.poll();
			
			try {
				Queue<MetablockConfidenceTuple> nextQueue = useSpatialRelation(currentBlock,relationKeywords.get(index),degrees.get(index));

				blocks = recursivlySearchForBlock(nextQueue,relationKeywords,degrees,index+1);
			} catch (Exception e) {
				//no blocks found in the given direction
			}
			
			if(blocks == null) {
				this.pathConfidenceValues.pop();
			}
			
		}
		
		return blocks;
	}

	public Queue<MetablockConfidenceTuple> useSpatialRelation (MetablockConfidenceTuple current, String spatialRelation,int degree) throws Exception{
		Queue<MetablockConfidenceTuple> out = new LinkedList<>();
		
		//Get corresponding 
		switch(spatialRelation){
			case "LEFT":
				out.addAll(current.metaBlock.left);
				break;
			case "RIGHT":
				out.addAll(current.metaBlock.right);
				break;
			case "FRONT":
				out.addAll(current.metaBlock.front);
				break;
			case "BEHIND":
				out.addAll(current.metaBlock.behind);
				break;
			//currently not able to sort by the distances between lists, later cna re calc distances to determine the closest blocks.
//			case "ADJACENT":
//				out.addAll(current.left);
//				out.addAll(current.right);
//				out.addAll(current.front);
//				out.addAll(current.behind);
//				break;
			default:
				throw new Exception("Unrecoginized spatial relation");
		}
		
		this.pathConfidenceValues.push(out.peek().confidence);
		
		//Figure get to the ith block away, where i is the degree.
		int i = degree;
		while(i > 1){
			out.poll();
			i--;
			if(out.isEmpty()){
				throw new Exception("Invalid degree: " + degree);
			}
		}
		
		
		if(out.isEmpty()){
			throw new Exception("No blocks found");
		}
		
		
		return out;
	}

}
