
/* Apache UIMA v3 - First created by JCasGen Wed Dec 12 23:28:16 EST 2018 */

package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class Feedback extends Annotation {

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static String _TypeName = "edu.rosehulman.aixprize.pipeline.types.Feedback";

	/**
	 * @generated
	 * @ordered
	 */
	@SuppressWarnings("hiding")
	public final static int typeIndexID = JCasRegistry.register(Feedback.class);
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
	protected Feedback() {
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
	public Feedback(int addr, TOP_Type type) {
		super(addr, type);
		readObject();
	}

	/**
	 * @generated
	 * @param jcas
	 *            JCas to which this Feature Structure belongs
	 */
	public Feedback(JCas jcas) {
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
	public Feedback(JCas jcas, int begin, int end) {
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
	// * Feature: feedbackMsg

	/**
	 * getter for feedbackMsg - gets
	 * 
	 * @generated
	 * @return value of the feature
	 */
	public double getFeedbackMsg() {
		if (Feedback_Type.featOkTst && ((Feedback_Type) jcasType).casFeat_feedbackMsg == null)
			jcasType.jcas.throwFeatMissing("feedbackMsg", "edu.rosehulman.aixprize.pipeline.types.Feedback");
		return jcasType.ll_cas.ll_getDoubleValue(addr, ((Feedback_Type) jcasType).casFeatCode_feedbackMsg);
	}

	/**
	 * setter for feedbackMsg - sets
	 * 
	 * @generated
	 * @param v
	 *            value to set into the feature
	 */
	public void setFeedbackMsg(double v) {
		if (Feedback_Type.featOkTst && ((Feedback_Type) jcasType).casFeat_feedbackMsg == null)
			jcasType.jcas.throwFeatMissing("feedbackMsg", "edu.rosehulman.aixprize.pipeline.types.Feedback");
		jcasType.ll_cas.ll_setDoubleValue(addr, ((Feedback_Type) jcasType).casFeatCode_feedbackMsg, v);
	}
}