package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class Color_Type extends Annotation_Type {
	/** @generated */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = Color.typeIndexID;
	/**
	 * @generated
	 * @modifiable
	 */
	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.Color");

	/** @generated */
	final Feature casFeat_id;
	/** @generated */
	final int casFeatCode_id;
	
	/** @generated */
	final Feature casFeat_color;
	/** @generated */
	final int casFeatCode_color;

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public String getColor(int addr) {
		if (featOkTst && casFeat_color == null)
			jcas.throwFeatMissing("color", "edu.rosehulman.aixprize.pipeline.types.Color");
		return ll_cas.ll_getStringValue(addr, casFeatCode_color);
	}
	
	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public double getId(int addr) {
		if (featOkTst && casFeat_color == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.Color");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_id);
	}

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setColor(int addr, String v) {
		if (featOkTst && casFeat_color == null)
			jcas.throwFeatMissing("color", "edu.rosehulman.aixprize.pipeline.types.Color");
		ll_cas.ll_setStringValue(addr, casFeatCode_color, v);
	}
	
	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setId(int addr, double v) {
		if (featOkTst && casFeat_id == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.Color");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_id, v);
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
	public Color_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Double", featOkTst);
		casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_id).getCode();
		
		casFeat_color = jcas.getRequiredFeatureDE(casType, "color", "uima.cas.String", featOkTst);
		casFeatCode_color = (null == casFeat_color) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_color).getCode();

	}
}