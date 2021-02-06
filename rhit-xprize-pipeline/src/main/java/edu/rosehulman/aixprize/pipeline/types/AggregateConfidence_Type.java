package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class AggregateConfidence_Type extends Annotation_Type {
	/** @generated */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = AggregateConfidence.typeIndexID;
	/**
	 * @generated
	 * @modifiable
	 */
	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");

	/** @generated */
	final Feature casFeat_id;
	/** @generated */
	final int casFeatCode_id;

	/** @generated */
	final Feature casFeat_confidence;
	/** @generated */
	final int casFeatCode_confidence;

	/** @generated */
	final Feature casFeat_normPointingConf;
	/** @generated */
	final int casFeatCode_normPointingConf;

	/** @generated */
	final Feature casFeat_normColorConf;
	/** @generated */
	final int casFeatCode_normColorConf;
	
	/** @generated */
	final Feature casFeat_spatialRelationshipConf;
	/** @generated */
	final int casFeatCode_spatialRelationshipConf;

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public double getConfidence(int addr) {
		if (featOkTst && casFeat_confidence == null)
			jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_confidence);
	}

	public double getNormPointingConf(int addr) {
		if (featOkTst && casFeat_normPointingConf == null)
			jcas.throwFeatMissing("normPointingConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_normPointingConf);
	}

	public double getNormColorConf(int addr) {
		if (featOkTst && casFeat_normColorConf == null)
			jcas.throwFeatMissing("normColorConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_normColorConf);
	}
	
	public double getSpatialRelationshipConf(int addr) {
		if (featOkTst && casFeat_spatialRelationshipConf == null)
			jcas.throwFeatMissing("spatialRelationshipConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_spatialRelationshipConf);
	}

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public double getId(int addr) {
		if (featOkTst && casFeat_confidence == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_id);
	}

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setConfidence(int addr, double v) {
		if (featOkTst && casFeat_confidence == null)
			jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_confidence, v);
	}
	
	public void setNormPointingConf(int addr, double v) {
		if (featOkTst && casFeat_normPointingConf == null)
			jcas.throwFeatMissing("normPointingConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_normPointingConf, v);
	}
	
	public void setNormColorConf(int addr, double v) {
		if (featOkTst && casFeat_normColorConf == null)
			jcas.throwFeatMissing("normColorConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_normColorConf, v);
	}
	
	public void setSpatialRelationshipConf(int addr, double v) {
		if (featOkTst && casFeat_spatialRelationshipConf == null)
			jcas.throwFeatMissing("spatialRelationshipConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_spatialRelationshipConf, v);
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
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
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
	public AggregateConfidence_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Double", featOkTst);
		casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl) casFeat_id).getCode();

		casFeat_confidence = jcas.getRequiredFeatureDE(casType, "confidence", "uima.cas.Double", featOkTst);
		casFeatCode_confidence = (null == casFeat_confidence) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_confidence).getCode();
		
		casFeat_normPointingConf = jcas.getRequiredFeatureDE(casType, "normPointingConf", "uima.cas.Double", featOkTst);
		casFeatCode_normPointingConf = (null == casFeat_normPointingConf) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_normPointingConf).getCode();
		
		casFeat_normColorConf = jcas.getRequiredFeatureDE(casType, "normColorConf", "uima.cas.Double", featOkTst);
		casFeatCode_normColorConf = (null == casFeat_normColorConf) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_normColorConf).getCode();
		
		casFeat_spatialRelationshipConf = jcas.getRequiredFeatureDE(casType, "spatialRelationshipConf", "uima.cas.Double", featOkTst);
		casFeatCode_spatialRelationshipConf = (null == casFeat_spatialRelationshipConf) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_spatialRelationshipConf).getCode();
	}
}