package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class ColorConfidence extends Annotation {

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static String _TypeName = "edu.rosehulman.aixprize.pipeline.types.ColorConfidence";

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(ColorConfidence.class);
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
	protected ColorConfidence() {
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
	public ColorConfidence(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public ColorConfidence(JCas jcas) {
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
	public ColorConfidence(JCas jcas, int begin, int end) {
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
		if (ColorConfidence_Type.featOkTst && ((ColorConfidence_Type) jcasType).casFeat_confidence == null)
			jcasType.jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((ColorConfidence_Type) jcasType).casFeatCode_confidence);
	}
	
	/**
	 * getter for confidence - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public double getId() {
		if (ColorConfidence_Type.featOkTst && ((ColorConfidence_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((ColorConfidence_Type) jcasType).casFeatCode_id);
	}

	/**
	 * setter for confidence - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setConfidence(double v) {
		if (ColorConfidence_Type.featOkTst && ((ColorConfidence_Type) jcasType).casFeat_confidence == null)
			jcasType.jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((ColorConfidence_Type) jcasType).casFeatCode_confidence, v);
	}
	
	/**
	 * setter for confidence - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setId(double v) {
		if (ColorConfidence_Type.featOkTst && ((ColorConfidence_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.ColorConfidence");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((ColorConfidence_Type) jcasType).casFeatCode_id, v);
	}
}