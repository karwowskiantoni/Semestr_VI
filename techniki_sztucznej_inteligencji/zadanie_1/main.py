import matplotlib.pyplot as plt
from tqdm import tqdm

from functions import rosenbrock, ROSENBROCK_DOMAIN, sphere, SPHERE_DOMAIN
from evolution.evolution import differential_evolution_algorithm
from zadanie_1.swarm.swarm import particle_swarm_optimization_algorithm

# RUN CONFIGURATION
STEPS = 30
BEGIN = 10
END = 50
i_function = lambda i: int( ((END - BEGIN) / STEPS) * i) + BEGIN

# COMMON ATTRIBUTES
FUNCTION = rosenbrock
DOMAIN = ROSENBROCK_DOMAIN
DIMENSIONS_NUMBER = 10
POPULATION_SIZE = 100
ITERATION_NUMBER = 50

# EVOLUTION ATTRIBUTES
AMPLIFICATION_FACTOR = 0.45
CROSSING_FACTOR = 0.1

# SWARM ATTRIBUTES
INERTIA_WEIGHT = 0.2
COGNITIVE_CONSTANT = 1
SOCIAL_CONSTANT = 2

if __name__ == '__main__':

    x_values = [i_function(i) for i in range(STEPS)]
    evolution_1_results = []
    evolution_2_results = []
    evolution_3_results = []
    swarm_1_results = []
    swarm_2_results = []
    swarm_3_results = []

    for i in tqdm(range(STEPS), ncols=100, position=0, colour="#2684ff"):

        evolution_1_results.append(differential_evolution_algorithm(
            function=rosenbrock,
            domain=ROSENBROCK_DOMAIN,
            dimensions_number=DIMENSIONS_NUMBER,
            # iteration_number=i_function(i),
            expected_fitness=i_function(i),
            population_size=POPULATION_SIZE,
            amplification_factor=AMPLIFICATION_FACTOR,
            crossing_factor=CROSSING_FACTOR
        ))

    # for i in tqdm(range(STEPS), ncols=100, position=0, colour="#f5459a"):
    #
    #     evolution_2_results.append(differential_evolution_algorithm(
    #         function=sphere,
    #         domain=SPHERE_DOMAIN,
    #         dimensions_number=DIMENSIONS_NUMBER,
    #         iteration_number=i_function(i),
    #         expected_fitness=i_function(i),
    #         population_size=POPULATION_SIZE,
    #         amplification_factor=AMPLIFICATION_FACTOR,
    #         crossing_factor=CROSSING_FACTOR
    #     ))

    # for i in tqdm(range(STEPS), ncols=100, position=0, colour="#f5459a"):
    #     swarm_1_results.append(particle_swarm_optimization_algorithm(
    #         function=rosenbrock,
    #         domain=ROSENBROCK_DOMAIN,
    #         dimensions_number=DIMENSIONS_NUMBER,
    #         iteration_number=i_function(i),
    #         population_size=POPULATION_SIZE,
    #         inertia_weight=INERTIA_WEIGHT,
    #         cognitive_constant=COGNITIVE_CONSTANT,
    #         social_constant=SOCIAL_CONSTANT
    #     ))
    #
    # for i in tqdm(range(STEPS), ncols=100, position=0, colour="#f5459a"):
    #     swarm_2_results.append(particle_swarm_optimization_algorithm(
    #         function=sphere,
    #         domain=SPHERE_DOMAIN,
    #         dimensions_number=DIMENSIONS_NUMBER,
    #         iteration_number=i_function(i),
    #         population_size=POPULATION_SIZE,
    #         inertia_weight=INERTIA_WEIGHT,
    #         cognitive_constant=COGNITIVE_CONSTANT,
    #         social_constant=SOCIAL_CONSTANT
    #     ))

    plt.plot(x_values, evolution_1_results, "#2684ff")
    # plt.plot(x_values, swarm_2_results, "#f5459a")
    # plt.legend(["rosenbrock", "sphere"])
    plt.xlabel("expected fitness")
    plt.ylabel("best result")
    plt.show()
