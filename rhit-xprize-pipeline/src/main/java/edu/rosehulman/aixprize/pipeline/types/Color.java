

/* First created by JCasGen Sun Oct 08 07:47:26 EDT 2017 */
package edu.rosehulman.aixprize.pipeline.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 17 17:10:22 EDT 2017
 * XML source: /mnt/ubuntu/home/lewis/aixprize_ws/src/edu_rosehulman_aixprize/pipeline/desc/ColorsAnnotatorDescriptor.xml
 * @generated */
public class Color extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Color.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Color() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Color(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Color(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Color(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: color

  /** getter for color - gets 
   * @generated
   * @return value of the feature 
   */
  public String getColor() {
    if (Color_Type.featOkTst && ((Color_Type)jcasType).casFeat_color == null)
      jcasType.jcas.throwFeatMissing("color", "edu.rosehulman.aixprize.pipeline.types.Color");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Color_Type)jcasType).casFeatCode_color);}
    
  /** setter for color - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setColor(String v) {
    if (Color_Type.featOkTst && ((Color_Type)jcasType).casFeat_color == null)
      jcasType.jcas.throwFeatMissing("color", "edu.rosehulman.aixprize.pipeline.types.Color");
    jcasType.ll_cas.ll_setStringValue(addr, ((Color_Type)jcasType).casFeatCode_color, v);}    
  }

    