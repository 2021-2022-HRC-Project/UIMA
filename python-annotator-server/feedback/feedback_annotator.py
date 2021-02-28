import json
from base_annotator import Annotator, AnnotationType

class FeedbackAnnotator(Annotator):
    RUN_AGAIN_MESSAGE = "I had trouble determining which block you are referring to; please try me again!"
    SUCCESS_MESSAGE = "I have found the block! I will try to pick it up!"
    AMBIGUITY_TEMPLATE = "I struggled to tell the difference between %d other block(s) near the selected block. Try me again!"
    AMBIGUITY_PERCENT_DIFF = 5


    def initialize(self):
        super().initialize()
        self.annotation_types.append(FeedbackAnnotation.ANNOTATION_UIMA_TYPE_NAME)

    def process(self, cas):
        all_filtered_blocks = cas['_views']['_InitialView']['FilteredBlock']
        selected_block_id = self.find_best_block_id(all_filtered_blocks)

        all_detected_blocks = cas['_views']['_InitialView']['DetectedBlock']
        agg_conf_scores = cas['_views']['_InitialView']['AggregateConfidence']
        feedback_msg = self.determine_feedback_msg(selected_block_id, all_detected_blocks, agg_conf_scores)
        print(feedback_msg)

        annotation = FeedbackAnnotation(feedback_msg)
        self.add_annotation(annotation)

    def find_best_block_id(self, all_filtered_blocks):
        best_block_id = -1
        for curr_idx in range(0, len(all_filtered_blocks)):
            block_id = all_filtered_blocks[curr_idx]['id']
            is_best_block = all_filtered_blocks[curr_idx]['isSelectedBlock']

            if is_best_block == 1:
                best_block_id = block_id
                return int(best_block_id)
        return best_block_id

    def determine_feedback_msg(self, selected_block_id, detected_blocks, agg_conf_scores):
        if selected_block_id == -1:
            return self.RUN_AGAIN_MESSAGE
        

        best_block_pointing_score = agg_conf_scores[selected_block_id]['normPointingConf']
        num_ambiguous = 0
        for curr_idx in range(0, len(detected_blocks)):
            curr_block_scores = agg_conf_scores[curr_idx]
            pointing_score = curr_block_scores['normPointingConf']

            percent_diff = abs(best_block_pointing_score - pointing_score) / best_block_pointing_score * 100
            if percent_diff <= self.AMBIGUITY_PERCENT_DIFF and curr_idx != best_block_pointing_score:
                num_ambiguous = num_ambiguous + 1

        if num_ambiguous > 0:
            return self.AMBIGUITY_TEMPLATE % (num_ambiguous)
            
        return self.SUCCESS_MESSAGE

class FeedbackAnnotation(AnnotationType):
    ANNOTATION_UIMA_TYPE_NAME = "edu.rosehulman.aixprize.pipeline.types.Feedback"
    
    def __init__(self, message):
        self.name = self.ANNOTATION_UIMA_TYPE_NAME
        self.feedbackMsg = message
