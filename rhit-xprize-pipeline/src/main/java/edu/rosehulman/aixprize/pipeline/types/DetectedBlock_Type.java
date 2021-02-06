package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class DetectedBlock_Type extends Annotation_Type {

	@SuppressWarnings("hiding")
	public final static int typeIndexID = DetectedBlock.typeIndexID;

	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.DetectedBlock");

	final Feature casFeat_id;
	final int casFeatCode_id;
	final Feature casFeat_center_X;
	final int casFeatCode_center_X;

	final Feature casFeat_center_Y;
	final int casFeatCode_center_Y;

	final Feature casFeat_camera_space_center_X;
	final int casFeatCode_camera_space_center_X;

	final Feature casFeat_camera_space_center_Y;
	final int casFeatCode_camera_space_center_Y;

	final Feature casFeat_camera_space_depth;
	final int casFeatCode_camera_space_depth;

	final Feature casFeat_r_hue;
	final int casFeatCode_r_hue;

	final Feature casFeat_g_hue;
	final int casFeatCode_g_hue;

	final Feature casFeat_b_hue;
	final int casFeatCode_b_hue;

	public double getId(int addr) {
		if (featOkTst && casFeat_id == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_id);
	}

	public void setId(int addr, double v) {
		if (featOkTst && casFeat_id == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_id, v);
	}

	public double getCenter_X(int addr) {
		if (featOkTst && casFeat_center_X == null)
			jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_center_X);
	}

	public void setCenter_X(int addr, double v) {
		if (featOkTst && casFeat_center_X == null)
			jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_center_X, v);
	}

	public double getCenter_Y(int addr) {
		if (featOkTst && casFeat_center_Y == null)
			jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_center_Y);
	}

	public void setCenter_Y(int addr, double v) {
		if (featOkTst && casFeat_center_Y == null)
			jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_center_Y, v);
	}

	public int getCamera_Space_Center_X(int addr) {
		if (featOkTst && casFeat_camera_space_center_X == null)
			jcas.throwFeatMissing("camera_space_center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getIntValue(addr, casFeatCode_camera_space_center_X);
	}

	public void setCamera_Space_Center_X(int addr, int v) {
		if (featOkTst && casFeat_camera_space_center_X == null)
			jcas.throwFeatMissing("camera_space_center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setIntValue(addr, casFeatCode_camera_space_center_X, v);
	}

	public int getCamera_Space_Center_Y(int addr) {
		if (featOkTst && casFeat_camera_space_center_Y == null)
			jcas.throwFeatMissing("camera_space_center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getIntValue(addr, casFeatCode_camera_space_center_Y);
	}

	public void setCamera_Space_Center_Y(int addr, int v) {
		if (featOkTst && casFeat_camera_space_center_Y == null)
			jcas.throwFeatMissing("casFeat_camera_space_center_Y",
					"edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setIntValue(addr, casFeatCode_camera_space_center_Y, v);
	}

	public int getCamera_Space_Depth(int addr) {
		if (featOkTst && casFeat_camera_space_depth == null)
			jcas.throwFeatMissing("camera_space_depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getIntValue(addr, casFeatCode_camera_space_depth);
	}

	public void setCamera_Space_Depth(int addr, int v) {
		if (featOkTst && casFeat_camera_space_depth == null)
			jcas.throwFeatMissing("depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setIntValue(addr, casFeatCode_camera_space_depth, v);
	}

	public double getR_Hue(int addr) {
		if (featOkTst && casFeat_r_hue == null)
			jcas.throwFeatMissing("r_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_r_hue);
	}

	public void setR_Hue(int addr, double v) {
		if (featOkTst && casFeat_r_hue == null)
			jcas.throwFeatMissing("r_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_r_hue, v);
	}

	public double getG_Hue(int addr) {
		if (featOkTst && casFeat_g_hue == null)
			jcas.throwFeatMissing("g_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_g_hue);
	}

	public void setG_Hue(int addr, double v) {
		if (featOkTst && casFeat_g_hue == null)
			jcas.throwFeatMissing("g_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_g_hue, v);
	}

	public double getB_Hue(int addr) {
		if (featOkTst && casFeat_b_hue == null)
			jcas.throwFeatMissing("b_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_b_hue);
	}

	public void setB_Hue(int addr, double v) {
		if (featOkTst && casFeat_b_hue == null)
			jcas.throwFeatMissing("b_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_b_hue, v);
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
	public DetectedBlock_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Double", featOkTst);
		casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_id).getCode();
		
		casFeat_center_X = jcas.getRequiredFeatureDE(casType, "center_X", "uima.cas.Double", featOkTst);
		casFeatCode_center_X = (null == casFeat_center_X) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_center_X).getCode();
		
		casFeat_center_Y = jcas.getRequiredFeatureDE(casType, "center_Y", "uima.cas.Double", featOkTst);
		casFeatCode_center_Y = (null == casFeat_center_Y) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_center_Y).getCode();
		
		casFeat_camera_space_center_X = jcas.getRequiredFeatureDE(casType, "camera_space_center_X", "uima.cas.Double", featOkTst);
		casFeatCode_camera_space_center_X = (null == casFeat_camera_space_center_X) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_camera_space_center_X).getCode();
		
		casFeat_camera_space_center_Y = jcas.getRequiredFeatureDE(casType, "camera_space_center_Y", "uima.cas.Double", featOkTst);
		casFeatCode_camera_space_center_Y = (null == casFeat_camera_space_center_Y) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_camera_space_center_Y).getCode();
		
		casFeat_camera_space_depth = jcas.getRequiredFeatureDE(casType, "camera_space_depth", "uima.cas.Double", featOkTst);
		casFeatCode_camera_space_depth = (null == casFeat_camera_space_depth) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_camera_space_depth).getCode();
		
		casFeat_r_hue = jcas.getRequiredFeatureDE(casType, "r_hue", "uima.cas.Double", featOkTst);
		casFeatCode_r_hue = (null == casFeat_r_hue) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_r_hue).getCode();
		
		casFeat_g_hue = jcas.getRequiredFeatureDE(casType, "g_hue", "uima.cas.Double", featOkTst);
		casFeatCode_g_hue = (null == casFeat_g_hue) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_g_hue).getCode();
		
		casFeat_b_hue = jcas.getRequiredFeatureDE(casType, "b_hue", "uima.cas.Double", featOkTst);
		casFeatCode_b_hue = (null == casFeat_b_hue) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_b_hue).getCode();
	}
}
