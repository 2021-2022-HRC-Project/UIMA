package SpatialRelationGenerator;

public class BlockWrapper implements Comparable<BlockWrapper>{

	public double distance;
	public InnerBlock block;
	public double confidence;
	
	public BlockWrapper(InnerBlock b, double distance, double confidence){
		this.distance = distance;
		this.block = b;
		this.confidence = confidence;
	}



	@Override
	public int compareTo(BlockWrapper otherBlock) {
		return (int) ((this.distance - otherBlock.distance) * 100);
	}
	
	@Override
	public String toString()
	{
		return block.name;
	}
	
}
