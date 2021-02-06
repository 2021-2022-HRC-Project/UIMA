

package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class CoordinateTransformation_Type extends Annotation_Type{
	@SuppressWarnings("hiding")
	public final static int typeIndexID = CoordinateTransformation.typeIndexID;

	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");

	final Feature casFeat_x;
	final int casFeatCode_x;

	final Feature casFeat_y;
	final int casFeatCode_y;

	final Feature casFeat_z;
	final int casFeatCode_z;

	public double getX(int addr) {
		if (featOkTst && casFeat_x == null)
			jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_x);
	}

	public void setX(int addr, double v) {
		if (featOkTst && casFeat_x == null)
			jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_x, v);
	}

	public double getY(int addr) {
		if (featOkTst && casFeat_y == null)
			jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_y);
	}

	public void setY(int addr, double v) {
		if (featOkTst && casFeat_y == null)
			jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_y, v);
	}

	public double getZ(int addr) {
		if (featOkTst && casFeat_z == null)
			jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_z);
	}

	public void setZ(int addr, double v) {
		if (featOkTst && casFeat_z == null)
			jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_z, v);
	}

	/**
	 * initialize variables to correspond with Cas Type and Features
	 * 
	 * @generated
	 * @param jcas
	 *            JCas
	 * @param casType
	 *            Type
	 */
	public CoordinateTransformation_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());
		
		casFeat_x = jcas.getRequiredFeatureDE(casType, "x", "uima.cas.Double", featOkTst);
		casFeatCode_x = (null == casFeat_x) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_x).getCode();
		
		casFeat_y = jcas.getRequiredFeatureDE(casType, "y", "uima.cas.Double", featOkTst);
		casFeatCode_y = (null == casFeat_y) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_y).getCode();
		
		casFeat_z= jcas.getRequiredFeatureDE(casType, "z", "uima.cas.Double", featOkTst);
		casFeatCode_z = (null == casFeat_z) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_z).getCode();
	}
}
