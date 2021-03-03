package SpatialRelationGenerator;

import org.apache.commons.math3.geometry.euclidean.threed.SphericalCoordinates;

public interface SpatialRelation {

	void checkRelation(SphericalCoordinates coords, BlockWrapper currentWrapper, BlockWrapper otherWrapper, InnerBlock current, InnerBlock other);
	
}
