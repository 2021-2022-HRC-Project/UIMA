
/* First created by JCasGen Sun Oct 08 07:47:26 EDT 2017 */
package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Oct 17 17:10:22 EDT 2017
 * @generated */
public class Color_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Color.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.rosehulman.aixprize.pipeline.types.Color");
 
  /** @generated */
  final Feature casFeat_color;
  /** @generated */
  final int     casFeatCode_color;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getColor(int addr) {
        if (featOkTst && casFeat_color == null)
      jcas.throwFeatMissing("color", "edu.rosehulman.aixprize.pipeline.types.Color");
    return ll_cas.ll_getStringValue(addr, casFeatCode_color);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setColor(int addr, String v) {
        if (featOkTst && casFeat_color == null)
      jcas.throwFeatMissing("color", "edu.rosehulman.aixprize.pipeline.types.Color");
    ll_cas.ll_setStringValue(addr, casFeatCode_color, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Color_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_color = jcas.getRequiredFeatureDE(casType, "color", "uima.cas.String", featOkTst);
    casFeatCode_color  = (null == casFeat_color) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_color).getCode();

  }
}



    