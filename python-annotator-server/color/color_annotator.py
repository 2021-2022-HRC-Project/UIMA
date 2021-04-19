from base_annotator import Annotator, AnnotationType
from color.rgb2lab import deltaE
from DataModel import ParsedResult
import json
from tornado.ioloop import IOLoop
from tornado.web import Application


class ColorAnnotation(AnnotationType):
    ANNOTATION_UIMA_TYPE_NAME = "edu.rosehulman.aixprize.pipeline.types.Color"

    def __init__(self, id, color):
        self.name = self.ANNOTATION_UIMA_TYPE_NAME
        self.id = id
        self.color = color


def is_word_in_str(target, string, start=0):
    idx = string.find(target)
    return idx >= 0


class ColorAnnotator(Annotator):
    def initialize(self):
        super().initialize()
        with open("./color/color_dictionary.json", encoding='utf-8') as f:  # open the color_dictionary.json file
            self.color_dict = json.load(f)
        self.annotation_types.append(ColorAnnotation.ANNOTATION_UIMA_TYPE_NAME)

    def process(self, cas):
        seq_num = cas['_views']['_InitialView']['NLPProcessor'][0]['seqNum']
        # with open("../NLPAnnotator/JSONOutput/outputJson" + seqNum +".json", encoding='utf-8') as f: # open the NLPOutpu json file
        #     nlp_result = json.load(f)
        # target_modifiers = nlp_result["edu.rosehulman.aixprize.pipeline.types.NLPProcessor"]["Target"]["mods"]

        parsed_result = ParsedResult.ParsedResult(seq_num)
        target_modifiers = parsed_result.target.mods
        referenceObjects = parsed_result.target.relationModel.objects
        # print(parsed_result.target.relationModel.objects[0].to_string())
        sofa_string = cas['_views']['_InitialView']['SpokenText'][0]['text']
        blocks = cas['_views']['_InitialView']['DetectedBlock']

        print("target_modifiers: ", target_modifiers)
        # TODO: Extract item blocks and their color,
        #  assign each color with highest confidence to block id from the spacial unit 4/4/2021
        # all_colors_in_text = []
        target_color = None
        # find the color of the target block
        for target_modifier in target_modifiers:
            if target_modifier.lower() in self.color_dict.keys():
                target_color = target_modifier.lower()

        # FIXME: the current version does not support relational color assignment. The following code do extract the
        #  color modifiers of the reference objects, but further logic should be implemented.
        reference_objects_color = None
        if target_color is None:
            reference_objects_color = []
            for obj in referenceObjects:
                for ref_mod in obj.mods:
                    if ref_mod.lower() in self.color_dict.keys():
                        reference_objects_color.append(ref_mod)

            print("all_colors_in_text: ", reference_objects_color)

        if reference_objects_color is None and target_color is None:
            print("Did not find color in spoken text, cannot determine confidence rating based on text.")
            return

        # TODO: Add relational logic when the target color is not given. (eg. Pick up the block to the
        #  left of the yellow block)

        # color_to_find = all_colors_in_text[0] # find the color key
        print("++++++++++++++++++++" + target_color)
        block_ids = []
        confidences = []
        for block in blocks:  # for each block's rgb value
            block_id = block['id']
            red_hue = block['r_hue']
            green_hue = block['g_hue']
            blue_hue = block['b_hue']

            block_ids.append(block_id)

            block_rgb = [red_hue, green_hue, blue_hue]
            # Scale rgb to put values in 0-255 range (adjusts for lighting)
            max_hue_value = max(block_rgb)
            scaled_rgb = [hue / max_hue_value * 255 for hue in block_rgb]

            analyzed_color_rgb = self.color_dict[target_color]
            deltaValue = deltaE(scaled_rgb, analyzed_color_rgb)
            # Assume that higher confidence is what we want given a color
            confidence = 1 / deltaValue if deltaValue != 0 else float.inf

            confidences.append(confidence)

        index = 0
        max_index = 0  # the current max confidence block id
        max_i = 0
        for confi in confidences:
            if (confi > max_i):
                max_i = confi
                max_index = index
            index = index + 1

        print("Block ID: " + str(block_ids[max_index]) + " Color: " + target_color)
        # annotation = ColorConfidenceAnnotation(block_id, confidence)
        annotation = ColorAnnotation(block_ids[max_index], target_color)  # assign the color to the block
        self.add_annotation(annotation)

    def rgb_dist(self, rgb1, rgb2):
        red_dist = (rgb1[0] - rgb2[0]) ** 2
        green_dist = (rgb1[1] - rgb2[1]) ** 2
        blue_dist = (rgb1[2] - rgb2[2]) ** 2

        return (red_dist + green_dist + blue_dist) ** 0.5
