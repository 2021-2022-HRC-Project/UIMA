import unittest
from text_processing.rgb2lab import deltaE

class TestRGB2lab(unittest.TestCase):

    def test_deltaE_same_color(self):
        color1 = [255,0,0]
        color2 = [255,0,0]
        
        assertAlmostEquals(0, deltaE(color1, color2))

    def test_deltaE_extrema(self):
        black = [0,0,0]
        white = [255,255,255]

        assertAlmostEquals(50, deltaE(white, black))

        