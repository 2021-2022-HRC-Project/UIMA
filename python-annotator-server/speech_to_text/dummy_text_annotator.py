import json
from base_annotator import Annotator, AnnotationType

class DummyTextAnnotator(Annotator):
    def initialize(self,):
        super().initialize()
        self.annotation_types.append(TextAnnotation.ANNOTATION_UIMA_TYPE_NAME)

    def process(self, cass):
        text = "move the green block"
        annotation = TextAnnotation(text)
        self.add_annotation(annotation)

class TextAnnotation(AnnotationType):
    ANNOTATION_UIMA_TYPE_NAME = "edu.rosehulman.aixprize.pipeline.types.SpokenText"
    
    def __init__(self, text):
        self.text = text
        self.name = self.ANNOTATION_UIMA_TYPE_NAME
