package developerAnnotators;

import dataStructures.Annotator;

import java.io.IOException;

public class DeveloperObjectDetectionAnnotator extends Annotator {
    private final String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.DetectedBlock\":";


    @Override
    public String process(String request) throws IOException {
        String result = "[{'id':1.0,'center_X':1035.0,'center_Y':935.0,'camera_space_center_X':-0.017150122672319412,'camera_space_center_Y':-0.27509960532188416,'camera_space_depth':0.8270000219345093,'r_hue':20.0,'g_hue':57.0,'b_hue':139.0},{'id':2.0,'center_X':1305.0,'center_Y':907.0,'camera_space_center_X':0.19513368606567383,'camera_space_center_Y':-0.25693538784980774,'camera_space_depth':0.8420000672340393,'r_hue':148.0,'g_hue':57.0,'b_hue':55.0},{'id':3.0,'center_X':1476.0,'center_Y':905.0,'camera_space_center_X':0.3265587091445923,'camera_space_center_Y':-0.25603410601615906,'camera_space_depth':0.8390000462532043,'r_hue':98.0,'g_hue':69.0,'b_hue':102.0},{'id':4.0,'center_X':575.0,'center_Y':730.0,'camera_space_center_X':-0.7604545950889587,'camera_space_center_Y':-0.2612585723400116,'camera_space_depth':1.873000144958496,'r_hue':120.0,'g_hue':116.0,'b_hue':105.0},{'id':5.0,'center_X':1047.0,'center_Y':692.0,'camera_space_center_X':0.07041464000940323,'camera_space_center_Y':-0.24406759440898895,'camera_space_depth':2.265000104904175,'r_hue':124.0,'g_hue':120.0,'b_hue':96.0},{'id':6.0,'center_X':422.0,'center_Y':513.0,'camera_space_center_X':-0.7150511741638184,'camera_space_center_Y':0.07545476406812668,'camera_space_depth':1.284000039100647,'r_hue':148.0,'g_hue':150.0,'b_hue':142.0},{'id':7.0,'center_X':469.0,'center_Y':498.0,'camera_space_center_X':-0.6903364062309265,'camera_space_center_Y':0.09681113064289093,'camera_space_depth':1.340000033378601,'r_hue':107.0,'g_hue':106.0,'b_hue':82.0},{'id':8.0,'center_X':1546.0,'center_Y':363.0,'camera_space_center_X':1.0376187562942505,'camera_space_center_Y':0.4118593633174896,'camera_space_depth':2.117000102996826,'r_hue':124.0,'g_hue':124.0,'b_hue':106.0},{'id':9.0,'center_X':746.0,'center_Y':510.0,'camera_space_center_X':-0.905585527420044,'camera_space_center_Y':0.23893511295318604,'camera_space_depth':3.881000280380249,'r_hue':19.0,'g_hue':14.0,'b_hue':7.0},{'id':10.0,'center_X':1109.0,'center_Y':476.0,'camera_space_center_X':0.1879386603832245,'camera_space_center_Y':0.19504773616790771,'camera_space_depth':2.124000072479248,'r_hue':120.0,'g_hue':119.0,'b_hue':91.0},{'id':11.0,'center_X':568.0,'center_Y':41.0,'camera_space_center_X':-1.4440279006958008,'camera_space_center_Y':1.8003621101379395,'camera_space_depth':3.8450002670288086,'r_hue':111.0,'g_hue':115.0,'b_hue':132.0}]";
        return "{"+unitWrapper+result+"}";
    }
}
