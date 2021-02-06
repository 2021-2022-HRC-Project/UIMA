package SpatialRelationGenerator;

import org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates;

public class Right implements SpatialRelation {

	@Override
	public void checkRelation(SphericalCoordinates coords, BlockWrapper currentWrapper, BlockWrapper otherWrapper,
			InnerBlock current, InnerBlock other) {
		if((coords.getTheta() < Math.PI/2.0) && (coords.getPhi() <= (3.0*Math.PI)/4.0)  && (coords.getPhi() > Math.PI/4.0)){
			current.right.add(otherWrapper);
			other.left.add(currentWrapper);
		}
	}

}
