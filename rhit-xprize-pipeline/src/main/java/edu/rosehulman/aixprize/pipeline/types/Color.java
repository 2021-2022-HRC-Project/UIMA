package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class Color extends Annotation {

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static String _TypeName = "edu.rosehulman.aixprize.pipeline.types.Color";

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(Color.class);
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
	protected Color() {
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
	public Color(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public Color(JCas jcas) {
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
	public Color(JCas jcas, int begin, int end) {
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
	 * getter for color - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public String getColor() {
		if (Color_Type.featOkTst && ((Color_Type) jcasType).casFeat_color == null)
			jcasType.jcas.throwFeatMissing("confidence", "edu.rosehulman.aixprize.pipeline.types.Color");
		return jcasType.ll_cas.ll_getStringValue(addr, ((Color_Type) jcasType).casFeatCode_color);
	}
	
	/**
	 * getter for id - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public double getId() {
		if (Color_Type.featOkTst && ((Color_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.Color");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((Color_Type) jcasType).casFeatCode_id);
	}

	/**
	 * setter for color - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setColor(String v) {
		if (Color_Type.featOkTst && ((Color_Type) jcasType).casFeat_color == null)
			jcasType.jcas.throwFeatMissing("color", "edu.rosehulman.aixprize.pipeline.types.Color");
		jcasType.ll_cas.ll_setStringValue(addr, ((Color_Type) jcasType).casFeatCode_color, v);
	}
	
	/**
	 * setter for id - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setId(double v) {
		if (Color_Type.featOkTst && ((Color_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.Color");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((Color_Type) jcasType).casFeatCode_id, v);
	}
}