import matplotlib.pyplot as plt
import numpy as np
from tqdm import tqdm

from functions import rosenbrock, ROSENBROCK_DOMAIN, sphere, SPHERE_DOMAIN
from evolution.evolution import differential_evolution_algorithm
from zadanie_1.swarm.swarm import particle_swarm_optimization_algorithm

# RUN CONFIGURATION
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

# EVOLUTION ATTRIBUTES
AMPLIFICATION_FACTOR = 0.45
CROSSING_FACTOR = 0.1

# SWARM ATTRIBUTES
INERTIA_WEIGHT = 0.2
COGNITIVE_CONSTANT = 1
SOCIAL_CONSTANT = 2


def evolution_with_params(customization=False):
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#2684ff"):
        results.append(differential_evolution_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=i_function(i),
            population_size=POPULATION_SIZE,
            amplification_factor=AMPLIFICATION_FACTOR,
            crossing_factor=CROSSING_FACTOR,
            customization=customization
        ))
    return results


def swarm_with_params(customization=False):
    results = []
    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#f5459a"):
        results.append(particle_swarm_optimization_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions=DIMENSIONS_NUMBER,
            iteration_number=i_function(i),
            population_size=POPULATION_SIZE,
            frequency_bounds=FREQUENCY_BOUNDS,
            pulse_rate=PULSE_RATE,
            pulse_rate_multiplier=PULSE_RATE_MULTIPLIER,
            loudness=LOUDNESS,
            loudness_multiplier=LOUDNESS_MULTIPLIER,
            customization=customization
        ))
    return results


if __name__ == '__main__':

    x_values = [i_function(i) for i in range(STEPS)]
    evolution_result_sets = []
    evolution_customization_result_sets = []
    # swarm_result_sets = []
    # swarm_customization_result_sets = []

    for i in range(10):
        evolution_result_sets.append(evolution_with_params())
        evolution_customization_result_sets.append(evolution_with_params(True))
        # swarm_result_sets.append(swarm_with_params())
        # swarm_customization_result_sets.append(swarm_with_params(True))

    evolution_averages = np.array(evolution_result_sets).mean(axis=0)
    evolution_customization_averages = np.array(evolution_customization_result_sets).mean(axis=0)
    # swarm_averages = np.array(swarm_result_sets).mean(axis=0)
    # swarm_customization_averages = np.array(swarm_customization_result_sets).mean(axis=0)

    plt.plot(x_values, evolution_averages, "#2684ff")
    plt.plot(x_values, evolution_customization_averages, "#f5459a")
    # plt.plot(x_values, swarm_averages, "#2684ff")
    # plt.plot(x_values, swarm_customization_averages, "#f5459a")
    plt.title(FUNCTION.__name__ + " function")
    plt.legend(["evolution", "evolution customized"])
    plt.xlabel("iteration number")
    plt.ylabel("best result")
    plt.show()


