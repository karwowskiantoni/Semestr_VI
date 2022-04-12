import matplotlib.pyplot as plt
import numpy as np
from tqdm import tqdm

from functions import rosenbrock, ROSENBROCK_DOMAIN, sphere, SPHERE_DOMAIN

from zadanie_2.bat.swarm import bat_optimization_algorithm
from zadanie_2.butterfly.algorithm import butterfly_optimization_algorithm

# RUN CONFIGURATION

STEPS = 50
BEGIN = 2
END = 50
i_function = lambda i: int(((END - BEGIN) / STEPS) * i) + BEGIN

# COMMON ATTRIBUTES
FUNCTION = rosenbrock
DOMAIN = ROSENBROCK_DOMAIN
DIMENSIONS_NUMBER = 20
POPULATION_SIZE = 50
ITERATION_NUMBER = 50
CUSTOMIZATION = False

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


def butterfly_with_params(measured_parameter=None):
    results = []
    for i in tqdm(range(STEPS), ncols=100, colour="#2684ff"):
        results.append(butterfly_optimization_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=ITERATION_NUMBER,
            population_size=i_function(i),
            sensor_modality=SENSOR_MODALITY,
            intensity_index=INTENSITY_INDEX,
            probability=PROBABILITY,
            customization=CUSTOMIZATION
        ))
    return results


def bat_with_params(measured_parameter=None):
    results = []
    for i in tqdm(range(STEPS), ncols=100, colour="#f5459a"):
        results.append(bat_optimization_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions=DIMENSIONS_NUMBER,
            iteration_number=ITERATION_NUMBER,
            population_size=i_function(i),
            frequency_bounds=FREQUENCY_BOUNDS,
            pulse_rate=PULSE_RATE,
            pulse_rate_multiplier=PULSE_RATE_MULTIPLIER,
            loudness=LOUDNESS,
            loudness_multiplier=LOUDNESS_MULTIPLIER,
            customization=CUSTOMIZATION
        ))
    return results


if __name__ == '__main__':
    x_values = [i_function(i) for i in range(STEPS)]

    result_sets_1 = [butterfly_with_params() for _ in range(10)]
    result_sets_2 = [bat_with_params() for _ in range(10)]

    plt.plot(x_values, np.array(result_sets_1).mean(axis=0), "#2684FF")
    plt.plot(x_values, np.array(result_sets_2).mean(axis=0), "#F5459A")

    plt.title(FUNCTION.__name__ + " function")
    plt.legend(["butterfly", "bat"])

    plt.xlabel("population size")
    plt.ylabel("best result")
    plt.show()
