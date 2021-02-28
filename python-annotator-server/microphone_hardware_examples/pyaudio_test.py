#!/usr/bin/env python3

import speech_recognition as sr
import pyaudio


r = sr.Recognizer()
with sr.Microphone() as source:
    print("Say something!")
    r.pause_threshold=1
    r.adjust_for_ambient_noise(source,duration=1) 
    audio = r.listen(source)

