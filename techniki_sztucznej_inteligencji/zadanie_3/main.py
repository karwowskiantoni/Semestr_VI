import matplotlib.pyplot as plt
import numpy as np
from tqdm import tqdm

from functions import \
    rosenbrock, ROSENBROCK_DOMAIN, \
    sphere, SPHERE_DOMAIN, \
    step, STEP_DOMAIN, \
    quartic, QUARTIC_DOMAIN, \
    exponential, EXPONENTIAL_DOMAIN, \
    f_two, F_TWO_DOMAIN, \
    schwefel, SCHWEFEL_DOMAIN

# RUN CONFIGURATION
from swarm.swarm import genetic_learning_particle_swarm_optimization_algorithm

STEPS = 20
BEGIN = 7
END = 27
i_function = lambda i: int( ((END - BEGIN) / STEPS) * i) + BEGIN

# COMMON ATTRIBUTES
FUNCTION = rosenbrock # 7 funkcji
DOMAIN = ROSENBROCK_DOMAIN # 7 funkcji
DIMENSIONS_NUMBER = 30 # 3 wersje
POPULATION_SIZE = 50 # 2 wersje
ITERATION_NUMBER = 20 # 2 wersje

# wymiary(d) 30, 50, 100
# funkcje(f) 1, 2, 3, 4, 5, 6, 7
# population size(p) 20, 50
# iteration number(i) 20, 50

#  default 30d, 50p, 20i

# gl-pso | f1 | 30d vs 50d vs 100d
# gl-pso | f1 | 20p vs 50p
# gl-pso | f1 | 20i vs 50i
# gl-pso | f1 vs f5 vs f6 | *

# gl-pso vs gl-pso-obl vs gl-pso-de | f1 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f2 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f3 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f4 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f5 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f6 | *
# gl-pso vs gl-pso-obl vs gl-pso-de | f7 | *


# SWARM ATTRIBUTES
INERTIA_WEIGHT = 0.2 # stała
MUTATION_PROBABILITY = 0.01 # stałe
STOPPING_GAP = 10000 # to kurwa nie istnieje tak naprawde
AMPLIFICATION_FACTOR = 0.45 # jest zajebisty nie ruszać


def genetic_learning_swarm_with_params(customized):
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#28732c"):
        results.append(genetic_learning_particle_swarm_optimization_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=customized,
            iteration_number=i_function(i),
            population_size=POPULATION_SIZE,
            inertia_weight=INERTIA_WEIGHT,
            mutation_probability=MUTATION_PROBABILITY,
            stopping_gap=STOPPING_GAP
        ))
    return results


def genetic_learning_swarm_obl_with_params():
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#cf2b67"):
        results.append(genetic_learning_particle_swarm_optimization_algorithm(
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


def genetic_learning_swarm_de_mutation_with_params():
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#3248a8"):
        results.append(genetic_learning_particle_swarm_optimization_algorithm(
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
    plt.plot(x_values, np.array([genetic_learning_swarm_with_params(30) for _ in range(5)]).mean(axis=0), "#28732c")
    plt.plot(x_values, np.array([genetic_learning_swarm_with_params(50) for _ in range(5)]).mean(axis=0), "#cf2b67")
    plt.plot(x_values, np.array([genetic_learning_swarm_with_params(100) for _ in range(5)]).mean(axis=0), "#3248a8")
    plt.title(FUNCTION.__name__ + " function")
    plt.legend(["30 dimensions", "50 dimensions", "100 dimensions"])
    plt.xlabel("iteration number")
    plt.ylabel("best result")
    plt.show()


