package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class FilteredBlock extends Annotation {

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static String _TypeName = "edu.rosehulman.aixprize.pipeline.types.FilteredBlock";

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(FilteredBlock.class);
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
	protected FilteredBlock() {
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
	public FilteredBlock(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public FilteredBlock(JCas jcas) {
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
	public FilteredBlock(JCas jcas, int begin, int end) {
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
	// * Feature: isSelectedBlock

	/**
	 * getter for isSelectedBlock - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public double getIsSelectedBlock() {
		if (FilteredBlock_Type.featOkTst && ((FilteredBlock_Type) jcasType).casFeat_isSelectedBlock == null)
			jcasType.jcas.throwFeatMissing("isSelectedBlock", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((FilteredBlock_Type) jcasType).casFeatCode_isSelectedBlock);
	}
	
	/**
	 * getter for isSelectedBlock - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public double getId() {
		if (FilteredBlock_Type.featOkTst && ((FilteredBlock_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((FilteredBlock_Type) jcasType).casFeatCode_id);
	}

	/**
	 * setter for isSelectedBlock - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setIsSelectedBlock(double v) {
		if (FilteredBlock_Type.featOkTst && ((FilteredBlock_Type) jcasType).casFeat_isSelectedBlock == null)
			jcasType.jcas.throwFeatMissing("isSelectedBlock", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((FilteredBlock_Type) jcasType).casFeatCode_isSelectedBlock, v);
	}
	
	/**
	 * setter for isSelectedBlock - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setId(double v) {
		if (FilteredBlock_Type.featOkTst && ((FilteredBlock_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((FilteredBlock_Type) jcasType).casFeatCode_id, v);
	}
}