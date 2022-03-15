import numpy as np


def uniform_noise(params, x):
    return (np.random.rand() * 2 * params.A) - params.A


def gauss_noise(params, x):
    return np.random.randn() * params.A  # todo why is this not generating numbers in range [0, 1]


def sinus(params, x):
    return params.A * np.sin(2 * np.pi / params.T * (x - params.t1))


def half_rectified_sinus(params, x):
    return 1 / 2 * params.A * ((np.sin((2 * np.pi) / params.T * (x - params.t1)))
                               + np.abs(np.sin((2 * np.pi) / params.T * (x - params.t1))))


def rectified_sinus(params, x):
    return params.A * np.abs(np.sin((2 * np.pi) / params.T * (x - params.t1)))


def rectangular(params, x):
    kT = (params.T * int(x / params.T))
    if kT + params.t1 <= x < (params.kw * params.T) + kT + params.t1:
        return params.A
    else:
        return 0


def symmetrical_rectangular(params, x):
    kT = (params.T * int(x / params.T))
    if kT + params.t1 <= x < (params.kw * params.T) + kT + params.t1:
        return params.A
    else:
        return -params.A


def triangular(params, x):
    kT = (params.T * int(x / params.T))
    if kT + params.t1 <= x < (params.kw * params.T) + kT + params.t1:
        return (params.A / (params.kw * params.T)) * (x - kT - params.t1)
    else:
        return -(params.A / (params.T * (1 - params.kw))) * (x - kT - params.t1) + (params.A / (1 - params.kw))


def unit_jump(parameters, x):
    if x > parameters.ts:
        return parameters.A
    elif x == parameters.ts:
        return 1 / 2 * parameters.A
    else:
        return 0


def unit_impulse(params, x):
    if x * params.f == params.ns:
        return params.A
    else:
        return 0


def noise_impulse(params, x):
    if np.random.rand() >= params.p:
        return params.A
    else:
        return 0

