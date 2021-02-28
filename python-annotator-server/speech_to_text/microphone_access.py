#!/usr/bin/env python3
import speech_recognition as sr

class MicrophoneProxy():
    def listen_for_snippet(self):
        raise NotImplemented('MicrophoneProxy must implement `listen_for_snippet` method')

class PyaudioMicrophone(MicrophoneProxy):
    PAUSE_THRESHOLD_SEC = 1
    MAX_ADJUST_SEC = 1

    def __init__(self):
        self.speech_recognizer = sr.Recognizer()

    def listen_for_snippet(self):
        with sr.Microphone() as source:
            print()
            print("Hello! I am adjusting for ambient noise, please wait...")
            self.speech_recognizer.pause_threshold = self.PAUSE_THRESHOLD_SEC
            self.speech_recognizer.adjust_for_ambient_noise(source, duration=self.MAX_ADJUST_SEC) 
            print("Please tell me your command!")
            audio = self.speech_recognizer.listen(source)
            return audio