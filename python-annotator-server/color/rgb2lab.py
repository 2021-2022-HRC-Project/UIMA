import numpy as np
import math

def func(t):
    if (t > 0.008856):
        return pow(t, 1/3.0)
    else:
        return 7.787 * t + 16 / 116.0

def rgb2lab(r, g, b):
    #Conversion Matrix
    matrix = [[0.412453, 0.357580, 0.180423],
            [0.212671, 0.715160, 0.072169],
            [0.019334, 0.119193, 0.950227]]

    # RGB values lie between 0 to 255
    rgb = [r/255, g/255, b/255] # RGB

    cie = np.dot(matrix, rgb)

    cie[0] = cie[0] /0.950456
    cie[2] = cie[2] /1.088754 

    # Calculate the L
    L = 116 * pow(cie[1], 1/3.0) - 16.0 if cie[1] > 0.008856 else 903.3 * cie[1]

    # Calculate the a 
    a = 500*(func(cie[0]) - func(cie[1]))

    # Calculate the b
    b = 200*(func(cie[1]) - func(cie[2]))

    #  Values lie between -128 < b <= 127, -128 < a <= 127, 0 <= L <= 100 
    Lab = [b , a, L] 

    # OpenCV Format
    L = L * 255 / 100
    a = a + 128
    b = b + 128
    Lab_OpenCV = [L, a, b] 

    return Lab_OpenCV

def deltaE(rgb1, rgb2):
    lab1 = rgb2lab(*rgb1)
    lab2 = rgb2lab(*rgb2)

    L1 = lab1[0]
    a1 = lab1[1]
    b1 = lab1[2]

    L2 = lab2[0]
    a2 = lab2[1]
    b2 = lab2[2]

    kL = 2
    k1 = 0.048
    k2 = 0.014
    kc = 1
    kh = 1

    deltaL = L1 - L2
    c1 = math.sqrt(a1**2 + b2**2)
    c2 = math.sqrt(a2**2 + b2**2)
    deltaC = c1 - c2
    deltaa = a1 - a2
    deltab = b1 - b2
    deltaH = math.sqrt((deltaa)**2 + (deltab)**2 - deltaC**2)
    sL = 1
    sC = 1 + k1*c1
    sH = 1 + k2*c1

    deltaE = math.sqrt((deltaL/(kL*sL))**2 + (deltaC/(kc*sC))**2 + (deltaH/(kh*sH))**2)

    return deltaE
