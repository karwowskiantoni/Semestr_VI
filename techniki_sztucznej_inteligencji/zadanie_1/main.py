import matplotlib.pyplot as plt
from tqdm import tqdm

from functions import rosenbrock, ROSENBROCK_DOMAIN
from evolution.evolution import differential_evolution_algorithm
from zadanie_1.swarm.swarm import particle_swarm_optimization_algorithm

# RUN CONFIGURATION
STEPS = 100
BEGIN = 0
END = STEPS
i_function = lambda i: ((END - BEGIN) / STEPS) * i + BEGIN

# COMMON ATTRIBUTES
FUNCTION = rosenbrock
DOMAIN = ROSENBROCK_DOMAIN
DIMENSIONS_NUMBER = 10
POPULATION_SIZE = 30
ITERATION_NUMBER = 30

# EVOLUTION ATTRIBUTES
AMPLIFICATION_FACTOR = 0.2
CROSSING_FACTOR = 0.2

# SWARM ATTRIBUTES
INERTIA_WEIGHT = 0.2
COGNITIVE_CONSTANT = 1
SOCIAL_CONSTANT = 2

if __name__ == '__main__':

    x_values = [i_function(i) for i in range(STEPS)]
    evolution_results = []
    swarm_results = []

    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#2684ff"):

        evolution_results.append(differential_evolution_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=ITERATION_NUMBER,
            population_size=POPULATION_SIZE,
            amplification_factor=AMPLIFICATION_FACTOR,
            crossing_factor=CROSSING_FACTOR
        ))

    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#f5459a"):
        swarm_results.append(particle_swarm_optimization_algorithm(
            function=FUNCTION,
            domain=DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            iteration_number=ITERATION_NUMBER,
            population_size=POPULATION_SIZE,
            inertia_weight=INERTIA_WEIGHT,
            cognitive_constant=COGNITIVE_CONSTANT,
            social_constant=SOCIAL_CONSTANT
        ))

    plt.plot(x_values, evolution_results, "#2684ff")
    plt.plot(x_values, swarm_results, "#f5459a")
    plt.legend(["evolution algorithm", "swarm algorithm"])
    plt.xlabel("crossing factor")
    plt.ylabel("best result")
    plt.show()
