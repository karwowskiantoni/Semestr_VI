from typing import List

from zadanie_1.decorator import np_cache

SPHERE_DOMAIN = tuple((-100, 100))


@np_cache
def sphere(params: List):
    return sum([x**2 for x in params])


ROSENBROCK_DOMAIN = tuple((-2.048, 2.048))


@np_cache
def rosenbrock(params):
    return sum([100 * (params[i + 1] - params[i]**2)**2 + (params[i] - 1)**2 for i in range(len(params) - 1)])
