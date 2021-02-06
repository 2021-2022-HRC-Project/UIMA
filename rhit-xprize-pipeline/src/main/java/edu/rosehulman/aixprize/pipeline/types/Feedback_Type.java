
/* First created by JCasGen Sun Oct 08 07:47:26 EDT 2017 */
package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class Feedback_Type extends Annotation_Type {
    /** @generated */
    @SuppressWarnings("hiding")
    public final static int typeIndexID = Feedback.typeIndexID;
    
    /**
     * @generated
     * @modifiable
     */
    @SuppressWarnings("hiding")
    public final static boolean featOkTst = JCasRegistry
            .getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.Feedback");

    /** @generated */
    final Feature casFeat_feedbackMsg;
    /** @generated */
    final int casFeatCode_feedbackMsg;

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @return the feature value
     */
    public double getFeedbackMsg(int addr) {
        if (featOkTst && casFeat_feedbackMsg == null)
            jcas.throwFeatMissing("feedbackMsg", "edu.rosehulman.aixprize.pipeline.types.Feedback");
        return ll_cas.ll_getDoubleValue(addr, casFeatCode_feedbackMsg);
    }

    /**
     * @generated
     * @param addr
     *            low level Feature Structure reference
     * @param v
     *            value to set
     */
    public void setFeedbackMsg(int addr, double v) {
        if (featOkTst && casFeat_feedbackMsg == null)
            jcas.throwFeatMissing("feedbackMsg", "edu.rosehulman.aixprize.pipeline.types.Feedback");
        ll_cas.ll_setDoubleValue(addr, casFeatCode_feedbackMsg, v);
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
    public Feedback_Type(JCas jcas, Type casType) {
        super(jcas, casType);
        casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

        casFeat_feedbackMsg = jcas.getRequiredFeatureDE(casType, "feedback", "uima.cas.String", featOkTst);
        casFeatCode_feedbackMsg = (null == casFeat_feedbackMsg) ? JCas.INVALID_FEATURE_CODE
                : ((FeatureImpl) casFeat_feedbackMsg).getCode();

    }
}