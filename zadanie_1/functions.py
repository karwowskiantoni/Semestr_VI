import numpy as np

FREQUENCY = 1  # Hz


def generate(function, start, length, amplitude):
    return [function(amplitude, x) for x in range(length - start * FREQUENCY)]


def uniform_noise(amplitude, x):
    return (np.random.rand() * 2 * amplitude) - amplitude


def gauss_noise(amplitude, x):
    return (np.random.randn() * 2 * amplitude) - amplitude


def sinus(amplitude, x):
    return 0
