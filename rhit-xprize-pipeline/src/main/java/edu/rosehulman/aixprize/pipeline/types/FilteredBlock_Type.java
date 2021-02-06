package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class FilteredBlock_Type extends Annotation_Type {
	/** @generated */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = FilteredBlock.typeIndexID;
	/**
	 * @generated
	 * @modifiable
	 */
	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.FilteredBlock");

	final Feature casFeat_id;
	final int casFeatCode_id;
	
	final Feature casFeat_isSelectedBlock;
	final int casFeatCode_isSelectedBlock;

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public double getIsSelectedBlock(int addr) {
		if (featOkTst && casFeat_isSelectedBlock == null)
			jcas.throwFeatMissing("isSelectedBlock", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_isSelectedBlock);
	}
	
	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public double getId(int addr) {
		if (featOkTst && casFeat_isSelectedBlock == null)
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
		return ll_cas.ll_getDoubleValue(addr, casFeatCode_id);
	}

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setIsSelectedBlock(int addr, double v) {
		if (featOkTst && casFeat_isSelectedBlock == null)
			jcas.throwFeatMissing("isSelectedBlock", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
		ll_cas.ll_setDoubleValue(addr, casFeatCode_isSelectedBlock, v);
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
			jcas.throwFeatMissing("id", "edu.rosehulman.aixprize.pipeline.types.FilteredBlock");
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
	public FilteredBlock_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Double", featOkTst);
		casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_id).getCode();
		
		casFeat_isSelectedBlock = jcas.getRequiredFeatureDE(casType, "isSelectedBlock", "uima.cas.Double", featOkTst);
		casFeatCode_isSelectedBlock = (null == casFeat_isSelectedBlock) ? JCas.INVALID_FEATURE_CODE
				: ((FeatureImpl) casFeat_isSelectedBlock).getCode();

	}
}