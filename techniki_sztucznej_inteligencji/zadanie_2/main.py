import matplotlib.pyplot as plt
import numpy as np
from tqdm import tqdm

from functions import rosenbrock, ROSENBROCK_DOMAIN, sphere, SPHERE_DOMAIN

from zadanie_2.bat.swarm import bat_optimization_algorithm
from zadanie_2.butterfly.algorithm import butterfly_optimization_algorithm

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

# BUTTERFLY ATTRIBUTES
SENSOR_MODALITY = 0.01
INTENSITY_INDEX = 0.4
PROBABILITY = 0.8

# BAT ATTRIBUTES
FREQUENCY_BOUNDS = tuple((0.0, 2.0))
PULSE_RATE = 0.5
PULSE_RATE_MULTIPLIER = 0.1
LOUDNESS = 0.5
LOUDNESS_MULTIPLIER = 0.1


def butterfly_with_params(customization=False):
    results = []
    for i in tqdm(range(STEPS), ncols=100, colour="#2684ff"):
        results.append(butterfly_optimization_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=i_function(i),
            population_size=POPULATION_SIZE,
            sensor_modality=SENSOR_MODALITY,
            intensity_index=INTENSITY_INDEX,
            probability=PROBABILITY,
            customization=customization
        ))
    return results


def bat_with_params(customization=False):
    results = []
    for i in tqdm(range(STEPS), ncols=100, colour="#f5459a"):
        results.append(bat_optimization_algorithm(
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

    butterfly_result_sets = []
    butterfly_customization_result_sets = []
    # bat_result_sets = []
    # swarm_customization_result_sets = []

    for i in range(10):
        butterfly_result_sets.append(butterfly_with_params())
        butterfly_customization_result_sets.append(butterfly_with_params(True))
        # bat_result_sets.append(bat_with_params(customization=True))
        # swarm_customization_result_sets.append(swarm_with_params(True))

    butterfly_averages = np.array(butterfly_result_sets).mean(axis=0)
    butterfly_customization_averages = np.array(butterfly_customization_result_sets).mean(axis=0)
    # bat_averages = np.array(bat_result_sets).mean(axis=0)
    # swarm_customization_averages = np.array(swarm_customization_result_sets).mean(axis=0)

    plt.plot(x_values, butterfly_averages, "#2684ff")
    plt.plot(x_values, butterfly_customization_averages, "#f5459a")
    # plt.plot(x_values, bat_averages, "#f5459a")
    # plt.plot(x_values, swarm_customization_averages, "#f5459a")

    plt.title(FUNCTION.__name__ + " function")
    plt.legend(["butterfly", "butterfly customized"])
    plt.xlabel("iteration number")
    plt.ylabel("best result")
    plt.show()


