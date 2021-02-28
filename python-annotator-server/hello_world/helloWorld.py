from base_annotator import Annotator, AnnotationType

class PythonHelloWorldAnnotator(Annotator):
    
    def initialize(self):
        super().initialize()
        self.annotation_types.append(PythonHelloWorldAnnotation.ANNOTATION_UIMA_TYPE_NAME)
        
    def process(self, cas):
        annotation = PythonHelloWorldAnnotation("Hello")
        self.add_annotation(annotation)
    
class PythonHelloWorldAnnotation(AnnotationType):
    ANNOTATION_UIMA_TYPE_NAME = "edu.rosehulman.aixprize.pipeline.types.PythonHelloWorld"
    
    def __init__(self, text):
        self.name = self.ANNOTATION_UIMA_TYPE_NAME
        self.text = text
        
        