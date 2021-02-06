package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class AggregateConfidence extends Annotation {

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static String _TypeName = "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence";

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(AggregateConfidence.class);
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
	protected AggregateConfidence() {
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
	public AggregateConfidence(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public AggregateConfidence(JCas jcas) {
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
	public AggregateConfidence(JCas jcas, int begin, int end) {
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

	/**
	 * getter for confidence - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public double getConfidence() {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_confidence == null)
			jcasType.jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_confidence);
	}
	
	public double getNormPointingConfidence() {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_normPointingConf == null)
			jcasType.jcas.throwFeatMissing("normPointingConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_normPointingConf);
	}
	
	public double getNormColorConfidence() {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_normColorConf == null)
			jcasType.jcas.throwFeatMissing("normColorConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_normColorConf);
	}
	
	public double getSpatialRelationshipConfidence() {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_spatialRelationshipConf == null)
			jcasType.jcas.throwFeatMissing("spatialRelationshipConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_spatialRelationshipConf);
	}
	
	/**
	 * getter for confidence - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public double getId() {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_id);
	}

	/**
	 * setter for confidence - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setConfidence(double v) {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_confidence == null)
			jcasType.jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_confidence, v);
	}
	
	public void setNormPointingConf(double v) {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_normPointingConf == null)
			jcasType.jcas.throwFeatMissing("normPointingConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_normPointingConf, v);
	}
	
	public void setNormColorConf(double v) {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_normColorConf == null)
			jcasType.jcas.throwFeatMissing("normColorConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_normColorConf, v);
	}
	
	public void setSpatialRelationshipConf(double v) {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_spatialRelationshipConf == null)
			jcasType.jcas.throwFeatMissing("spatialRelationshipConf", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_spatialRelationshipConf, v);
	}
	
	/**
	 * setter for confidence - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setId(double v) {
		if (AggregateConfidence_Type.featOkTst && ((AggregateConfidence_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.AggregateConfidence");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((AggregateConfidence_Type) jcasType).casFeatCode_id, v);
	}
}