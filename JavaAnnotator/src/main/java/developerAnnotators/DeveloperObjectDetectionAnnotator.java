package developerAnnotators;

import dataStructures.Annotator;

import java.io.IOException;

public class DeveloperObjectDetectionAnnotator extends Annotator {
    private final String unitWrapper = "\"edu.rosehulman.aixprize.pipeline.types.DetectedBlock\":";


    @Override
    public String process(String request) throws IOException {
        String result = "[{'id':1.0,'center_X':754.0,'center_Y':587.0,'camera_space_center_X':-0.2151883989572525,'camera_space_center_Y':-0.0091502470895648,'camera_space_depth':0.7630000114440918,'r_hue':109.0,'g_hue':106.0,'b_hue':76.0},{'id':2.0,'center_X':1544.0,'center_Y':575.0,'camera_space_center_X':0.3066319525241852,'camera_space_center_Y':-7.678611436858773E-4,'camera_space_depth':0.6960000395774841,'r_hue':91.0,'g_hue':62.0,'b_hue':95.0},{'id':3.0,'center_X':1301.0,'center_Y':574.0,'camera_space_center_X':0.1538895219564438,'camera_space_center_Y':0.0011505488073453307,'camera_space_depth':0.7110000252723694,'r_hue':138.0,'g_hue':51.0,'b_hue':51.0},{'id':4.0,'center_X':1054.0,'center_Y':567.0,'camera_space_center_X':-0.008937851525843143,'camera_space_center_Y':0.005005259532481432,'camera_space_depth':0.7050000429153442,'r_hue':20.0,'g_hue':52.0,'b_hue':127.0},{'id':5.0,'center_X':964.0,'center_Y':189.0,'camera_space_center_X':-0.08546193689107895,'camera_space_center_Y':0.5728806257247925,'camera_space_depth':1.6030000448226929,'r_hue':133.0,'g_hue':129.0,'b_hue':95.0}]";
        return "{"+unitWrapper+result+"}";
    }
}
