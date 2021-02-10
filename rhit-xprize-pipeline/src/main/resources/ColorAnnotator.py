from Annotation import Annotation
from Annotator import Annotator

import json
from tornado.ioloop import IOLoop
from tornado.web import Application


class Color(Annotation):
    def __init__(self, color, start, end):
        self.color = color
        self.begin = start
        self.end = end

    @staticmethod
    def name():
        return "edu.rosehulman.aixprize.pipeline.types.Color"

def find_in_str(target, string, start=0):
    idx = string.find(target)
    if idx >= 0:
        return [ Color(target, start + idx, start + idx + len(target) - 1) ] + \
            find_in_str(target, string[idx + 1:], start=start + idx + 1)
    return [ ]

class ColorAnnotator(Annotator):
    def initialize(self):
        super().initialize()
        self.color_words = ['red', 'blue', 'yellow']
        self.annotation_types.append(Color.name())

    def process(self, cas):
        sofa_string = cas['_referenced_fss']['1']['sofaString']
        to_analyze = sofa_string
        for word in self.color_words:
            anns = find_in_str(word, to_analyze)
            for a in anns:
                self.add_annotation(a)


if __name__ == '__main__':
    app = Application([
        ('/', ColorAnnotator)
    ])
    app.listen(8080)
    IOLoop.current().start()
