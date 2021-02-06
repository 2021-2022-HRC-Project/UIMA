

package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class MetadataSelectedBlock_Type extends Annotation_Type{
	@SuppressWarnings("hiding")
	public final static int typeIndexID = MetadataSelectedBlock.typeIndexID;

	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");

	final Feature casFeat_id;
	final int casFeatCode_id;
	
	final Feature casFeat_x;
	final int casFeatCode_x;

	final Feature casFeat_y;
	final int casFeatCode_y;

	final Feature casFeat_z;
	final int casFeatCode_z;
	
	final Feature casFeat_confidenceValue;
	final int casFeatCode_confidenceValue;

	final Feature casFeat_left;
	final int casFeatCode_left;

	final Feature casFeat_right;
	final int casFeatCode_right;

	final Feature casFeat_behind;
	final int casFeatCode_behind;

	final Feature casFeat_front;
	final int casFeatCode_front;

	final Feature casFeat_name;
	final int casFeatCode_name;

	public double getId(int addr) {
		if (featOkTst && casFeat_id == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_id);
	}

	public void setId(int addr, double v) {
		if (featOkTst && casFeat_id == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_id, v);
	}

	public double getX(int addr) {
		if (featOkTst && casFeat_x == null)
			jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_x);
	}

	public void setX(int addr, double v) {
		if (featOkTst && casFeat_x == null)
			jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_x, v);
	}

	public double getY(int addr) {
		if (featOkTst && casFeat_y == null)
			jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_y);
	}

	public void setY(int addr, double v) {
		if (featOkTst && casFeat_y == null)
			jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_y, v);
	}

	public double getZ(int addr) {
		if (featOkTst && casFeat_z == null)
			jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_z);
	}

	public void setZ(int addr, double v) {
		if (featOkTst && casFeat_z == null)
			jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_z, v);
	}

	public double getConfidenceValue(int addr) {
		if (featOkTst && casFeat_confidenceValue == null)
			jcas.throwFeatMissing("confidenceValue", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_confidenceValue);
	}

	public void setConfidenceValue(int addr, double v) {
		if (featOkTst && casFeat_confidenceValue == null)
			jcas.throwFeatMissing("confidenceValue", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_confidenceValue, v);
	}
	
	public String getLeft(int addr) {
		if (featOkTst && casFeat_left == null)
			jcas.throwFeatMissing("left", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_left);
	}

	public void setLeft(int addr, String v) {
		if (featOkTst && casFeat_left == null)
			jcas.throwFeatMissing("left", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_left, v);
	}

	public String getRight(int addr) {
		if (featOkTst && casFeat_right == null)
			jcas.throwFeatMissing("right", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_right);
	}

	public void setRight(int addr, String v) {
		if (featOkTst && casFeat_right == null)
			jcas.throwFeatMissing("right", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_right, v);
	}

	public String getBehind(int addr) {
		if (featOkTst && casFeat_behind == null)
			jcas.throwFeatMissing("behind", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_behind);
	}

	public void setBehind(int addr, String v) {
		if (featOkTst && casFeat_behind == null)
			jcas.throwFeatMissing("behind", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_behind, v);
	}

	public String getFront(int addr) {
		if (featOkTst && casFeat_front == null)
			jcas.throwFeatMissing("front", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_front);
	}

	public void setFront(int addr, String v) {
		if (featOkTst && casFeat_front == null)
			jcas.throwFeatMissing("front", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_front, v);
	}

	public String getName(int addr) {
		if (featOkTst && casFeat_name == null)
			jcas.throwFeatMissing("name", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return ll_cas.ll_getStringValue(addr, casFeatCode_name);
	}

	public void setName(int addr, String v) {
		if (featOkTst && casFeat_name == null)
			jcas.throwFeatMissing("name", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		ll_cas.ll_setStringValue(addr, casFeatCode_name, v);
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
	public MetadataSelectedBlock_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Double", featOkTst);
		casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_id).getCode();
		
		casFeat_x = jcas.getRequiredFeatureDE(casType, "x", "uima.cas.Double", featOkTst);
		casFeatCode_x = (null == casFeat_x) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_x).getCode();
		
		casFeat_y = jcas.getRequiredFeatureDE(casType, "y", "uima.cas.Double", featOkTst);
		casFeatCode_y = (null == casFeat_y) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_y).getCode();
		
		casFeat_z= jcas.getRequiredFeatureDE(casType, "z", "uima.cas.Double", featOkTst);
		casFeatCode_z = (null == casFeat_z) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_z).getCode();
		
		casFeat_confidenceValue= jcas.getRequiredFeatureDE(casType, "confidenceValue", "uima.cas.Double", featOkTst);
		casFeatCode_confidenceValue = (null == casFeat_confidenceValue) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_confidenceValue).getCode();
		
		casFeat_left = jcas.getRequiredFeatureDE(casType, "left", "uima.cas.String", featOkTst);
		casFeatCode_left = (null == casFeat_left) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_left).getCode();
		
		casFeat_right = jcas.getRequiredFeatureDE(casType, "right", "uima.cas.String", featOkTst);
		casFeatCode_right = (null == casFeat_right) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_right).getCode();
		
		casFeat_behind = jcas.getRequiredFeatureDE(casType, "behind", "uima.cas.String", featOkTst);
		casFeatCode_behind = (null == casFeat_behind) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_behind).getCode();
		
		casFeat_front = jcas.getRequiredFeatureDE(casType, "front", "uima.cas.String", featOkTst);
		casFeatCode_front = (null == casFeat_front) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_front).getCode();
		
		casFeat_name = jcas.getRequiredFeatureDE(casType, "name", "uima.cas.String", featOkTst);
		casFeatCode_name = (null == casFeat_name) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_name).getCode();
	}
}
