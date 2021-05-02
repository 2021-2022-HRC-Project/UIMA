from base_annotator import Annotator, AnnotationType
from target_extraction.rgb2lab import deltaE
from DataModel import ParsedResult, SpatialRelationBlock
import json

from dataclasses import dataclass


@dataclass
class TargetAnnotation(AnnotationType):
    ANNOTATION_UIMA_TYPE_NAME = "edu.rosehulman.aixprize.pipeline.types.Color"
    id: float
    color: str

    def __init__(self, id, color):
        self.name = self.ANNOTATION_UIMA_TYPE_NAME
        self.id = id
        self.color = color


def is_word_in_str(target, string, start=0):
    idx = string.find(target)
    return idx >= 0


class TargetAnnotator(Annotator):
    def initialize(self):
        super().initialize()
        with open("./target_extraction/color_dictionary.json", encoding='utf-8') as f:  # open the color_dictionary.json file
            self.color_dict = json.load(f)
        self.annotation_types.append(TargetAnnotation.ANNOTATION_UIMA_TYPE_NAME)

    def process(self, cas):
        seq_num = cas['_views']['_InitialView']['NLPProcessor'][0]['seqNum']
        block_list_raw = cas['_views']['_InitialView']['SpatialRelationBlock']
        block_list = {}
        for block in block_list_raw:
            block_list[block["id"]] = \
                SpatialRelationBlock.SpatialRelationBlock(block["id"],
                                                          block["name"],
                                                          block["x"],
                                                          block["y"],
                                                          block["z"],
                                                          block["left"],
                                                          block["right"],
                                                          block["front"],
                                                          block["behind"])
        print(block_list)
        # with open("../NLPAnnotator/JSONOutput/outputJson" + seqNum +".json", encoding='utf-8') as f: # open the NLPOutpu json file
        #     nlp_result = json.load(f)
        # target_modifiers = nlp_result["edu.rosehulman.aixprize.pipeline.types.NLPProcessor"]["Target"]["mods"]

        parsed_result = ParsedResult.ParsedResult(seq_num)
        target_modifiers = parsed_result.target.mods
        reference_objects = parsed_result.target.relationModel.objects
        # print(parsed_result.target.relationModel.objects[0].to_string())
        sofa_string = cas['_views']['_InitialView']['SpokenText'][0]['text']
        blocks = cas['_views']['_InitialView']['DetectedBlock']

        print("target_modifiers: ", target_modifiers)

        # all_colors_in_text = []
        target_color = None
        # find the color of the target block
        for target_modifier in target_modifiers:
            if target_modifier.lower() in self.color_dict.keys():
                target_color = target_modifier.lower()

        if target_color is not None:
            # color_to_find = all_colors_in_text[0] # find the color key
            print("++++++++++++++++++++" + target_color)
            target_id = self.assign_color(blocks, target_color)
            annotation = TargetAnnotation(target_id, target_color)  # assign the color to the block
            print(annotation)
            self.add_annotation(annotation)
            return
        # FIXME: the current version does not support relational color assignment. The following code do extract the
        #  color modifiers of the reference objects, but further logic should be implemented.
        else:
            reference_objects_color = []
            for ref_obj in reference_objects:
                for ref_mod in ref_obj.mods:
                    if ref_mod.lower() in self.color_dict.keys():
                        ref_obj.color = ref_mod.lower()
                        ref_obj.id = self.assign_color(blocks, ref_mod.lower())
                        reference_objects_color.append(ref_mod)
            direction = parsed_result.target.relationModel.direction

            if direction == "???" or not reference_objects_color:
                # TODO: use pointing result or if check if there is only one object
                raise NotImplementedError
            if direction.lower() == "between":
                # TODO two blocks
                ref_obj1 = reference_objects[0].id
                ref_obj2 = reference_objects[1].id
                target_obj1_left = block_list[ref_obj1].spatial_dict['left']
                target_obj1_right = block_list[ref_obj1].spatial_dict['right']
                target_obj2_left = block_list[ref_obj2].spatial_dict['left']
                target_obj2_right = block_list[ref_obj2].spatial_dict['right']
                intersect_list1 = self.check_intersect_target(target_obj1_left, target_obj2_right)
                intersect_list2 = self.check_intersect_target(target_obj1_right, target_obj2_left)
                if not intersect_list1 and not intersect_list2:
                    annotation = TargetAnnotation(-1, "???")
                    self.add_annotation(annotation)
                    print(annotation)
                    return
                elif intersect_list1:
                    annotation = TargetAnnotation(intersect_list1, "???")
                    self.add_annotation(annotation)
                    print(annotation)
                    return
                elif intersect_list2:
                    annotation = TargetAnnotation(intersect_list2, "???")
                    self.add_annotation(annotation)
                    print(annotation)
                    return

            else:
                ref_obj = reference_objects[0]
                ref_id = ref_obj.id
                ref_color = ref_obj.color
                target_dict = block_list[ref_id].spatial_dict[direction]
                target_id = max(target_dict, key=target_dict.get)
                annotation = TargetAnnotation(target_id, "???")  # assign the color to the block
                self.add_annotation(annotation)
                print(annotation)
                return

            print("all_colors_in_reference_object: ", reference_objects_color)

        # TODO: Add relational logic when the target color is not given. (eg. Pick up the block to the
        #  left of the yellow block)

    def check_intersect_target(self, obj1, obj2):
        inter_list = set(obj1.keys()) & set(obj2.keys());
        if not inter_list:
            return False
        else:
            inter_dict = {}
            for key in inter_list:
                inter_dict[key] = (obj1[key] + obj2[key]) / 2
            return max(inter_dict, key=inter_dict.get)

    def assign_color(self, blocks, target_color):
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
            if confi > max_i:
                max_i = confi
                max_index = index
            index = index + 1

        print("Block ID: " + str(block_ids[max_index]) + " Color: " + target_color)
        # annotation = ColorConfidenceAnnotation(block_id, confidence)
        return block_ids[max_index]

    # TODO: find a better way to distinguish the blue block and the purple block. Currently,
    #  there is a problem that when finding purple block, the blue block is detected and assigned id

    def rgb_dist(self, rgb1, rgb2):
        red_dist = (rgb1[0] - rgb2[0]) ** 2
        green_dist = (rgb1[1] - rgb2[1]) ** 2
        blue_dist = (rgb1[2] - rgb2[2]) ** 2

        return (red_dist + green_dist + blue_dist) ** 0.5
