from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop
from tornado.options import define, options
from tornado.web import Application

from speech_to_text.dummy_text_annotator import DummyTextAnnotator
from speech_to_text.speech_to_text_annotator import SpeechToTextAnnotator
from speech_to_text.microphone_access import PyaudioMicrophone
from speech_to_text.speech_recognizer_software import GoogleCloudSpeechConverter
from color_confidence.color_annotator import ColorConfidenceAnnotator
from confidence_aggregation.aggregate_confidence_annotator import AggregateConfidenceAnnotator
from feedback.feedback_annotator import FeedbackAnnotator
from CoordinateTransformation.transformation import CoordinateTransformationAnnotator 
from hello_world.helloWorld import PythonHelloWorldAnnotator

define('port', default=3000, help='port to listen on')

def main():
    """Construct and serve the tornado application."""
    audio_source = PyaudioMicrophone()
    speech_converter = GoogleCloudSpeechConverter()

    print("starting py server")
    app = Application(handlers= [
        ('/Speech', SpeechToTextAnnotator,
            {"audio_source": audio_source,
             "speech_processor": speech_converter}),
        ('/TextWithoutSpeech', DummyTextAnnotator),
        ('/ColorConfidence', ColorConfidenceAnnotator),
        ('/AggregateConfidence', AggregateConfidenceAnnotator),
        ('/Feedback', FeedbackAnnotator),
        ('/CoordTransformation', CoordinateTransformationAnnotator),
        ('/PythonHelloWorld', PythonHelloWorldAnnotator)
    ])
    http_server = HTTPServer(app)
    http_server.listen(options.port)
    IOLoop.current().start()

if __name__ == '__main__':
    main()
