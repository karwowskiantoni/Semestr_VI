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
COGNITIVE_CONSTANT = 1
SOCIAL_CONSTANT = 2
MUTATION_PROBABILITY = 0.01
STOPPING_GAP = 10000


def genetic_learning_swarm_with_params():
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#fffa69"):
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


if __name__ == '__main__':

    x_values = [i_function(i) for i in range(STEPS)]

    genetic_swarm_result_sets = []

    for i in range(5):
        genetic_swarm_result_sets.append(genetic_learning_swarm_with_params())

    genetic_swarm_averages = np.array(genetic_swarm_result_sets).mean(axis=0)

    plt.plot(x_values, genetic_swarm_averages, "#28732c")
    plt.title(FUNCTION.__name__ + " function")
    plt.legend(["genetic"])
    plt.xlabel("iteration number")
    plt.ylabel("best result")
    plt.show()


