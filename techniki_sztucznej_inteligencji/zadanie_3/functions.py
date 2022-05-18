from numba import njit
import numpy as np

sphere_domain = tuple((-100, 100))


@njit()
def sphere(x):
    return np.sum(x**2)


step_domain = tuple((-10, 10))


@njit()
def step(x):
    return np.sum((x + 0.5)**2)


quartic_domain = tuple((-1.28, 1.28))


@njit()
def quartic(x):
    return np.sum(np.arange(x.size) * x**2 + np.random.random(x.size))


exponential_domain = tuple((-10, 10))


@njit()
def exponential(x):
    return np.exp(0.5 * np.sum(x))


f_two_domain = tuple((-100, 100))


@njit()
def f_two(x):
    return np.sum(x - np.arange(x.size))


schwefel_domain = tuple((-10, 10))


@njit()
def schwefel(x):
    return np.sum(np.abs(x)**2) + np.prod(np.abs(x))


rosenbrock_domain = tuple((-2.048, 2.048))


@njit()
def rosenbrock(x):
    return sum(100.0*(x[1:]-x[:-1]**2.0)**2.0 + (1-x[:-1])**2.0)
