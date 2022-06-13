import numpy as np


def uniform_noise(params, x):
    return (np.random.rand() * 2 * params.A) - params.A


def gauss_noise(params, x):
    return np.random.randn()


def sinus(params, x):
    T = 1 / params.signal_f
    return params.A * np.sin(2 * np.pi / T * (x - params.t1))


def half_rectified_sinus(params, x):
    T = 1 / params.signal_f
    return 1 / 2 * params.A * ((np.sin((2 * np.pi) / T * (x - params.t1)))
                               + np.abs(np.sin((2 * np.pi) / T * (x - params.t1))))


def rectified_sinus(params, x):
    T = 1 / params.signal_f
    return params.A * np.abs(np.sin((2 * np.pi) / T * (x - params.t1)))


def rectangular(params, x):
    T = 1 / params.signal_f
    kT = (T * int(x / T))
    if kT + params.t1 <= x < (params.kw * T) + kT + params.t1:
        return params.A
    else:
        return 0


def symmetrical_rectangular(params, x):
    T = 1 / params.signal_f
    kT = (T * int(x / T))
    if kT + params.t1 <= x < (params.kw * T) + kT + params.t1:
        return params.A
    else:
        return -params.A


def triangular(params, x):
    T = 1 / params.signal_f
    kT = (T * int(x / T))
    if kT + params.t1 <= x < (params.kw * T) + kT + params.t1:
        return (params.A / (params.kw * T)) * (x - kT - params.t1)
    else:
        return -(params.A / (T * (1 - params.kw))) * (x - kT - params.t1) + (params.A / (1 - params.kw))


def unit_jump(parameters, x):
    if x > parameters.ts:
        return parameters.A
    elif x == parameters.ts:
        return 1 / 2 * parameters.A
    else:
        return 0


def unit_impulse(params, x):
    if int(x * params.sampling_f) == params.ns:
        return params.A
    else:
        return 0


def noise_impulse(params, x):
    if np.random.rand() <= params.p:
        return params.A
    else:
        return 0


def blackman_window(M, x):
    shit = (2 * np.pi * x) / M
    return 0.42 - (0.5 * np.cos(shit)) + 0.08 * np.cos(2 * shit)


def low_pass_to_mid_pass(x):
    return 2 * np.sin(np.pi * (x / 2))


def impulse_response(M, cutoff_f, sampling_f, x):
    K = sampling_f / cutoff_f
    mid_M = (M - 1) / 2
    if x == mid_M:
        return 2 / K
    else:
        return np.sin((2 * np.pi * (x - mid_M)) / K) / (np.pi * (x - mid_M))
