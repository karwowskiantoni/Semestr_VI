from numba import njit
import numpy as np

SPHERE_DOMAIN = tuple((-100, 100))


@njit()
def sphere(x):
    return np.sum(x**2)


STEP_DOMAIN = tuple((-10, 10))


@njit()
def step(x):
    return np.sum((x + 0.5)**2)


QUARTIC_DOMAIN = tuple((-1.28, 1.28))


@njit()
def quartic(x):
    return np.sum(np.arange(x.size) * x**2 + np.random.random(x.size))


EXPONENTIAL_DOMAIN = tuple((-10, 10))


@njit()
def exponential(x):
    return np.exp(0.5 * np.sum(x))


F_TWO_DOMAIN = tuple((-100, 100))


@njit()
def f_two(x):
    return np.sum(x - np.arange(x.size))


SCHWEFEL_DOMAIN = tuple((-10, 10))


@njit()
def schwefel(x):
    return np.sum(np.abs(x)**2) + np.prod(np.abs(x))


ROSENBROCK_DOMAIN = tuple((-2.048, 2.048))


@njit()
def rosenbrock(x):
    return sum(100.0*(x[1:]-x[:-1]**2.0)**2.0 + (1-x[:-1])**2.0)
