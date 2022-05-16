from random import random

import matplotlib.pyplot as plt
import numpy as np
from tqdm import tqdm

from swarm.swarm import gl_pso
from functions import \
    rosenbrock, ROSENBROCK_DOMAIN, \
    sphere, SPHERE_DOMAIN, \
    step, STEP_DOMAIN, \
    quartic, QUARTIC_DOMAIN, \
    exponential, EXPONENTIAL_DOMAIN, \
    f_two, F_TWO_DOMAIN, \
    schwefel, SCHWEFEL_DOMAIN


STEPS = 20
BEGIN = 5
END = 25
i_function = lambda i: int(((END - BEGIN) / STEPS) * i) + BEGIN

FUNCTION = exponential  # 7 funkcji
DOMAIN = EXPONENTIAL_DOMAIN  # 7 funkcji
DIMENSIONS_NUMBER = 30  # 3 wersje
POPULATION_SIZE = 50  # 2 wersje
ITERATION_NUMBER = 20  # 2 wersje
INERTIA_WEIGHT = 0.2  # stała
MUTATION_PROBABILITY = 0.01  # stała
STOPPING_GAP = 10000  # stała
AMPLIFICATION_FACTOR = 0.45  # stała

# wymiary(d) 30, 50, 100
# funkcje(f) 1, 2, 3, 4, 5, 6, 7
# population size(p) 20, 50
# iteration number(i) 20, 50

#  default 30d, 50p, 20i

# gl-pso | f1 | 30d vs 50d vs 100d
# gl-pso | f1 | 20p vs 50p
# gl-pso | f1 | 20i vs 50i
# gl-pso | f4 vs f5 vs f6 | *

# gl-pso vs gl-pso-obl vs gl-pso-de | f1 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f2 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f3 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f4 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f5 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f6 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f7 | *


def gl_pso_default():
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#28732c"):
        results.append(gl_pso(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=i_function(i),
            population_size=POPULATION_SIZE,
            inertia_weight=INERTIA_WEIGHT,
            mutation_probability=MUTATION_PROBABILITY,
            stopping_gap=STOPPING_GAP
        ))
    return results


def gl_pso_obl_default():
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#cf2b67"):
        results.append(gl_pso(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=i_function(i),
            population_size=POPULATION_SIZE,
            inertia_weight=INERTIA_WEIGHT,
            mutation_probability=MUTATION_PROBABILITY,
            stopping_gap=STOPPING_GAP,
            obl=True
        ))
    return results


def gl_pso_de_default():
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#3248a8"):
        results.append(gl_pso(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=i_function(i),
            population_size=POPULATION_SIZE,
            inertia_weight=INERTIA_WEIGHT,
            mutation_probability=AMPLIFICATION_FACTOR,
            stopping_gap=STOPPING_GAP,
            de_mutation=True
        ))
    return results


if __name__ == '__main__':

    x_values = [i_function(i) for i in range(STEPS)]
    plt.plot(x_values, np.array([gl_pso_default() for _ in range(5)]).mean(axis=0), "#4287f5")
    plt.plot(x_values, np.array([gl_pso_obl_default() for _ in range(5)]).mean(axis=0), "#eb2a2a")
    plt.plot(x_values, np.array([gl_pso_de_default() for _ in range(5)]).mean(axis=0), "#28732c")
    plt.title("schwefel function")
    plt.legend(["gl_pso", "gl_pso_obl", "gl_pso_de"])
    plt.xlabel("iteration number")
    plt.ylabel("best result")
    plt.show()


