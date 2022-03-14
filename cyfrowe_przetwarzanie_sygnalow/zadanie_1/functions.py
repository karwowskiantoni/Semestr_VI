import numpy as np


def uniform_noise(parameters, x):
    return (np.random.rand() * 2 * parameters.A) - parameters.A


def gauss_noise(parameters, x):
    return np.random.randn() * parameters.A  # todo why is this not generating numbers in range [0, 1]


def sinus(parameters, x):
    return parameters.A * np.sin(2 * np.pi/parameters.T * (x - parameters.t1))
