from base_annotator import Annotator, AnnotationType
from color_confidence.rgb2lab import deltaE

import json
from tornado.ioloop import IOLoop
from tornado.web import Application

class ColorConfidenceAnnotation(AnnotationType):
    ANNOTATION_UIMA_TYPE_NAME = "edu.rosehulman.aixprize.pipeline.types.ColorConfidence"

    def __init__(self, id, confidence):
        self.name = self.ANNOTATION_UIMA_TYPE_NAME
        self.id = id
        self.confidence = confidence

def is_word_in_str(target, string, start=0):
    idx = string.find(target)
    return idx >= 0

class ColorConfidenceAnnotator(Annotator):
    def initialize(self):
        super().initialize()
        with open("./color_confidence/color_dictionary.json", encoding='utf-8') as f:
            self.color_dict = json.load(f)
        self.annotation_types.append(ColorConfidenceAnnotation.ANNOTATION_UIMA_TYPE_NAME)

    def process(self, cas):
        sofa_string = cas['_views']['_InitialView']['SpokenText'][0]['text']
        blocks = cas['_views']['_InitialView']['DetectedBlock']
       
        to_analyze = sofa_string
        all_colors_in_text = []
        for word in self.color_dict.keys():
            if is_word_in_str(word, to_analyze.lower()):
                all_colors_in_text.append(word)

        if len(all_colors_in_text) == 0:
            print("Did not find color in spoken text, cannot determine confidence rating based on text.")
            return
        
        color_to_find = all_colors_in_text[0]
        for block in blocks:
            block_id = block['id']
            red_hue = block['r_hue']
            green_hue = block['g_hue']
            blue_hue = block['b_hue']


            block_rgb = [red_hue, green_hue, blue_hue]
            # Scale rgb to put values in 0-255 range (adjusts for lighting)
            max_hue_value = max(block_rgb)
            scaled_rgb = [hue / max_hue_value * 255 for hue in block_rgb]
        
            analyzed_color_rgb = self.color_dict[color_to_find]
            deltaValue = deltaE(scaled_rgb, analyzed_color_rgb)
            confidence = 1 / deltaValue if deltaValue != 0 else 0

            annotation = ColorConfidenceAnnotation(block_id, confidence)
            self.add_annotation(annotation)
    
    def rgb_dist(self, rgb1, rgb2):
        red_dist = (rgb1[0] - rgb2[0]) ** 2
        green_dist = (rgb1[1] - rgb2[1]) ** 2
        blue_dist = (rgb1[2] - rgb2[2]) ** 2

        return (red_dist + green_dist + blue_dist) ** 0.5
