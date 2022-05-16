import matplotlib.pyplot as plt
import numpy as np
from tqdm import tqdm

from functions import rosenbrock, ROSENBROCK_DOMAIN, sphere, SPHERE_DOMAIN

# RUN CONFIGURATION
from swarm.swarm import genetic_learning_particle_swarm_optimization_algorithm

STEPS = 50
BEGIN = 5
END = 55
i_function = lambda i: int( ((END - BEGIN) / STEPS) * i) + BEGIN

# COMMON ATTRIBUTES
FUNCTION = rosenbrock
DOMAIN = ROSENBROCK_DOMAIN
DIMENSIONS_NUMBER = 20
POPULATION_SIZE = 50
ITERATION_NUMBER = 35

# SWARM ATTRIBUTES
INERTIA_WEIGHT = 0.2
MUTATION_PROBABILITY = 0.01
STOPPING_GAP = 10000
AMPLIFICATION_FACTOR = 0.45


def genetic_learning_swarm_with_params():
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#28732c"):
        results.append(genetic_learning_particle_swarm_optimization_algorithm(
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

    genetic_swarm_result_sets = []
    genetic_swarm_obl_result_sets = []
    genetic_swarm_de_result_sets = []

    for i in range(20):
        genetic_swarm_result_sets.append(genetic_learning_swarm_with_params())
        genetic_swarm_obl_result_sets.append(genetic_learning_swarm_obl_with_params())
        genetic_swarm_de_result_sets.append(genetic_learning_swarm_de_mutation_with_params())

    genetic_swarm_averages = np.array(genetic_swarm_result_sets).mean(axis=0)
    genetic_swarm_obl_averages = np.array(genetic_swarm_obl_result_sets).mean(axis=0)
    genetic_swarm_de_averages = np.array(genetic_swarm_de_result_sets).mean(axis=0)

    plt.plot(x_values, genetic_swarm_averages, "#28732c")
    plt.plot(x_values, genetic_swarm_obl_averages, "#cf2b67")
    plt.plot(x_values, genetic_swarm_de_averages, "#3248a8")
    plt.title(FUNCTION.__name__ + " function")
    plt.legend(["genetic", "obl", "de_mutation"])
    plt.xlabel("iteration number")
    plt.ylabel("best result")
    plt.show()


