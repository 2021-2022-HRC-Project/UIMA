package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class MetadataSelectedBlock extends Annotation{
	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(MetadataSelectedBlock.class);
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
	protected MetadataSelectedBlock() {
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
	public MetadataSelectedBlock(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public MetadataSelectedBlock(JCas jcas) {
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
	public MetadataSelectedBlock(JCas jcas, int begin, int end) {
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
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_id);
	}

	public void setId(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_id == null)
			jcasType.jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_id, v);
	}
	
	public double getx(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_x == null)
			jcasType.jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_x);
	}

	public void setx(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_x == null)
			jcasType.jcas.throwFeatMissing("x", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_x, v);
	}
	
	public double gety(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_y == null)
			jcasType.jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_y);
	}

	public void sety(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_y == null)
			jcasType.jcas.throwFeatMissing("y", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_y, v);
	}
	
	public double getz(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_z == null)
			jcasType.jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_z);
	}

	public void setz(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_z == null)
			jcasType.jcas.throwFeatMissing("z", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_z, v);
	}
	
	public double getconfidenceValue(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_confidenceValue == null)
			jcasType.jcas.throwFeatMissing("confidenceValue", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_confidenceValue);
	}

	public void setconfidenceValue(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_confidenceValue == null)
			jcasType.jcas.throwFeatMissing("confidenceValue", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_confidenceValue, v);
	}
	
	public int getleft(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_left == null)
			jcasType.jcas.throwFeatMissing("left", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_left);
	}

	public void setleft(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_left == null)
			jcasType.jcas.throwFeatMissing("casFeat_left", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_left, v);
	}
	
	public int getright(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_right == null)
			jcasType.jcas.throwFeatMissing("right", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_right);
	}

	public void setright(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_right == null)
			jcasType.jcas.throwFeatMissing("depth", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_right, v);
	}
	
	public int getbehind(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_behind == null)
			jcasType.jcas.throwFeatMissing("behind", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_behind);
	}

	public void setbehind(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_behind == null)
			jcasType.jcas.throwFeatMissing("behind", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_behind, v);
	}
	
	public int getfront(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_front == null)
			jcasType.jcas.throwFeatMissing("front", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_front);
	}

	public void setfront(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_front == null)
			jcasType.jcas.throwFeatMissing("front", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_front, v);
	}
	
	public int getname(int addr) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_name == null)
			jcasType.jcas.throwFeatMissing("name", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		return jcasType.ll_cas.ll_getIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_name);
	}

	public void setname(int addr, int v) {
		if (MetadataSelectedBlock_Type.featOkTst && ((MetadataSelectedBlock_Type) jcasType).casFeat_name == null)
			jcasType.jcas.throwFeatMissing("name", "edu.rosehulman.aixprize.pipeline.types.MetadataSelectedBlock");
		jcasType.ll_cas.ll_setIntValue(addr, ((MetadataSelectedBlock_Type) jcasType).casFeatCode_name, v);
	}
}
