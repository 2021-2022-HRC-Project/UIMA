#!/usr/bin/env python3
import speech_recognition as sr
import traceback

class SpeechConverter():
    def process_sr_audio(self, audio_snippet):
        raise NotImplemented('SpeechConverters must implement `process_sr_audio` method')

class GoogleCloudSpeechConverter(SpeechConverter):
    SECRETS_PATH = "./auth_secrets/rhit-human-robot-collab-8e5fac3432af.json"

    def __init__(self):
        self.speech_recognizer = sr.Recognizer()

    def process_sr_audio(self, audio_snippet):
        credentials = open(self.SECRETS_PATH).read()

        # Log errors to console if they occur
        try:
            processed_text = self.speech_recognizer.recognize_google_cloud(audio_snippet, credentials_json=credentials)
            print("I think you said: " + processed_text)
            return processed_text
        except sr.UnknownValueError:
            print("My friend Google Cloud Speech could not understand audio")
            traceback.print_exc()
            return ""
        except sr.RequestError as e:
            print("Could not request results from my friend, Google Cloud Speech service; {0}".format(e))
            traceback.print_exc()
            return ""
        

