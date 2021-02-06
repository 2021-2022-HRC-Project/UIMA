package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class ColorConfidence_Type extends Annotation_Type {
	/** @generated */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = ColorConfidence.typeIndexID;
	/**
	 * @generated
	 * @modifiable
	 */
	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.ColorConfidence");

	/** @generated */
	final Feature casFeat_id;
	/** @generated */
	final int casFeatCode_id;
	
	/** @generated */
	final Feature casFeat_confidence;
	/** @generated */
	final int casFeatCode_confidence;

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public double getConfidence(int addr) {
		if (featOkTst && casFeat_confidence == null)
			jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_confidence);
	}
	
	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public double getId(int addr) {
		if (featOkTst && casFeat_confidence == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_id);
	}

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setConfidece(int addr, double v) {
		if (featOkTst && casFeat_confidence == null)
			jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_confidence, v);
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
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
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
	public ColorConfidence_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Double", featOkTst);
		casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_id).getCode();
		
		casFeat_confidence = jcas.getRequiredFeatureDE(casType, "confidence", "uima.cas.Double", featOkTst);
		casFeatCode_confidence = (null == casFeat_confidence) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_confidence).getCode();

	}
}