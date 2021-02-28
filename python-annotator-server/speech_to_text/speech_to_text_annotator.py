import json
from base_annotator import Annotator, AnnotationType

class SpeechToTextAnnotator(Annotator):
    def initialize(self, audio_source, speech_processor):
        super().initialize()
        self.audio_source = audio_source
        self.speech_processor = speech_processor
        self.annotation_types.append(TextAnnotation.ANNOTATION_UIMA_TYPE_NAME)

    def process(self, cass):
        audio_snippet = self.audio_source.listen_for_snippet()
        converted_text = self.speech_processor.process_sr_audio(audio_snippet)
        annotation = TextAnnotation(converted_text)
        self.add_annotation(annotation)

class TextAnnotation(AnnotationType):
    ANNOTATION_UIMA_TYPE_NAME = "edu.rosehulman.aixprize.pipeline.types.SpokenText"
    
    def __init__(self, text):
        self.text = text
        self.name = self.ANNOTATION_UIMA_TYPE_NAME
