
/* First created by JCasGen Sun Oct 08 07:47:26 EDT 2017 */
package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation_Type;

/**
 * Updated by JCasGen Tue Oct 17 17:10:22 EDT 2017
 * 
 * @generated
 */
public class MemorySave_Type extends Annotation_Type {
	/** @generated */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = MemorySave.typeIndexID;
	/**
	 * @generated
	 * @modifiable
	 */
	@SuppressWarnings("hiding")
	public final static boolean featOkTst = JCasRegistry
			.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.MemorySave");

	/** @generated */
	final Feature casFeat_text;
	/** @generated */
	final int casFeatCode_text;

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @return the feature value
	 */
	public String getText(int addr) {
		if (featOkTst && casFeat_text == null)
			jcas.throwFeatMissing("namedBlocks", "edu.rosehulman.aixprize.pipeline.types.MemorySave");
		return ll_cas.ll_getStringValue(addr, casFeatCode_text);
	}

	/**
	 * @generated
	 * @param addr
	 *            low level Feature Structure reference
	 * @param v
	 *            value to set
	 */
	public void setText(int addr, String v) {
		if (featOkTst && casFeat_text == null)
			jcas.throwFeatMissing("namedBlocks", "edu.rosehulman.aixprize.pipeline.types.MemorySave");
		ll_cas.ll_setStringValue(addr, casFeatCode_text, v);
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
	public MemorySave_Type(JCas jcas, Type casType) {
		super(jcas, casType);
		casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

		casFeat_text = jcas.getRequiredFeatureDE(casType, "namedBlocks", "uima.cas.String", featOkTst);
		casFeatCode_text = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl) casFeat_text).getCode();

	}
}
