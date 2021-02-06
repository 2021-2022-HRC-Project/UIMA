
/* Apache UIMA v3 - First created by JCasGen Wed Dec 12 23:28:16 EST 2018 */

package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class DetectedBlock extends Annotation {
	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(DetectedBlock.class);
	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int type = typeIndexID;

	/**
	 * @generated
	 * @return index of the type
	 */
	@Override
	public int getTypeIndexID() {
		return typeIndexID;
	}

	/**
	 * Never called. Disable default constructor
	 * 
	 * @generated
	 */
	protected DetectedBlock() {
		/* intentionally empty block */}

	/**
	 * Internal - constructor used by generator
	 * 
	 * @generated
	 * @param casImpl
	 *            the CAS this Feature Structure belongs to
	 * @param type
	 *            the type of this Feature Structure
	 */
	public DetectedBlock(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public DetectedBlock(JCas jcas) {
		super(jcas);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 * @param begin
	 *            offset to the begin spot in the SofA
	 * @param end
	 *            offset to the end spot in the SofA
	 */
	public DetectedBlock(JCas jcas, int begin, int end) {
		super(jcas);
		setBegin(begin);
		setEnd(end);
		readObject();
	}

	/**
	 * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc
	 * -->
	 *
	 * @generated modifiable
	 */
	private void readObject() {
		/* default - does nothing empty block */}

	// *--------------*
	// * Feature: confidence

	public int getId(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_id);
	}

	public void setId(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_id, v);
	}
	
	public int getCenter_X(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_X == null)
			jcasType.jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_X);
	}

	public void setCenter_X(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_X == null)
			jcasType.jcas.throwFeatMissing("center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_X, v);
	}
	
	public int getCenter_Y(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_Y == null)
			jcasType.jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_Y);
	}

	public void setCenter_Y(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_center_Y == null)
			jcasType.jcas.throwFeatMissing("center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_center_Y, v);
	}
	
	public int getCamera_Space_Center_X(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_camera_space_center_X == null)
			jcasType.jcas.throwFeatMissing("camera_space_center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_camera_space_center_X);
	}

	public void setCamera_Space_Center_X(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_camera_space_center_X == null)
			jcasType.jcas.throwFeatMissing("camera_space_center_X", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_camera_space_center_X, v);
	}
	
	public int getCamera_Space_Center_Y(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_camera_space_center_Y == null)
			jcasType.jcas.throwFeatMissing("camera_space_center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_camera_space_center_Y);
	}

	public void setCamera_Space_Center_Y(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_camera_space_center_Y == null)
			jcasType.jcas.throwFeatMissing("casFeat_camera_space_center_Y", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_camera_space_center_Y, v);
	}
	
	public int getCamera_Space_Depth(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_camera_space_depth == null)
			jcasType.jcas.throwFeatMissing("camera_space_depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_camera_space_depth);
	}

	public void setCamera_Space_Depth(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_camera_space_depth == null)
			jcasType.jcas.throwFeatMissing("depth", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_camera_space_depth, v);
	}
	
	public int getR_Hue(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_r_hue == null)
			jcasType.jcas.throwFeatMissing("r_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_r_hue);
	}

	public void setR_Hue(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_r_hue == null)
			jcasType.jcas.throwFeatMissing("r_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_r_hue, v);
	}
	
	public int getG_Hue(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_g_hue == null)
			jcasType.jcas.throwFeatMissing("g_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_g_hue);
	}

	public void setG_Hue(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_g_hue == null)
			jcasType.jcas.throwFeatMissing("g_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_g_hue, v);
	}
	
	public int getB_Hue(int addr) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_b_hue == null)
			jcasType.jcas.throwFeatMissing("b_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_b_hue);
	}

	public void setB_Hue(int addr, int v) {
		if (DetectedBlock_Type.featOkTst && ((DetectedBlock_Type) jcasType).casFeat_b_hue == null)
			jcasType.jcas.throwFeatMissing("b_hue", "edu.rosehulman.aixprize.pipeline.types.DetectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((DetectedBlock_Type) jcasType).casFeatCode_b_hue, v);
	}
}
