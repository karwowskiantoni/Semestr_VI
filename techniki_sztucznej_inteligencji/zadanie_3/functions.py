from typing import List
from numba import njit

SPHERE_DOMAIN = tuple((-100, 100))


@njit()
def sphere(params: List):
    return sum([x**2 for x in params])


ROSENBROCK_DOMAIN = tuple((-2.048, 2.048))


@njit()
def rosenbrock(x):
    return sum(100.0*(x[1:]-x[:-1]**2.0)**2.0 + (1-x[:-1])**2.0)
