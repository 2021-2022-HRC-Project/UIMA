package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class CoordinateTransformation extends Annotation{
	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(CoordinateTransformation.class);
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
	protected CoordinateTransformation() {
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
	public CoordinateTransformation(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public CoordinateTransformation(JCas jcas) {
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
	public CoordinateTransformation(JCas jcas, int begin, int end) {
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
	
	public double getx(int addr) {
		if (CoordinateTransformation_Type.featOkTst && ((CoordinateTransformation_Type) jcasType).casFeat_x == null)
			jcasType.jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((CoordinateTransformation_Type) jcasType).casFeatCode_x);
	}

	public void setx(int addr, int v) {
		if (CoordinateTransformation_Type.featOkTst && ((CoordinateTransformation_Type) jcasType).casFeat_x == null)
			jcasType.jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((CoordinateTransformation_Type) jcasType).casFeatCode_x, v);
	}
	
	public double gety(int addr) {
		if (CoordinateTransformation_Type.featOkTst && ((CoordinateTransformation_Type) jcasType).casFeat_y == null)
			jcasType.jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((CoordinateTransformation_Type) jcasType).casFeatCode_y);
	}

	public void sety(int addr, int v) {
		if (CoordinateTransformation_Type.featOkTst && ((CoordinateTransformation_Type) jcasType).casFeat_y == null)
			jcasType.jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((CoordinateTransformation_Type) jcasType).casFeatCode_y, v);
	}
	
	public double getz(int addr) {
		if (CoordinateTransformation_Type.featOkTst && ((CoordinateTransformation_Type) jcasType).casFeat_z == null)
			jcasType.jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((CoordinateTransformation_Type) jcasType).casFeatCode_z);
	}

	public void setz(int addr, int v) {
		if (CoordinateTransformation_Type.featOkTst && ((CoordinateTransformation_Type) jcasType).casFeat_z == null)
			jcasType.jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.CoordinateTransformation");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((CoordinateTransformation_Type) jcasType).casFeatCode_z, v);
	}
}
