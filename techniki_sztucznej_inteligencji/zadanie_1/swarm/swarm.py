import matplotlib.pyplot as plt
from zadanie_1.functions import *
import Particle
import numpy as np


ITERATIONS = 1000


def particle_swarm_optimization_algorithm(function, dimensions, inertia_weight, cognitive_constant, social_constant, domain, population_size):
    Particle.DIMENSIONS = dimensions
    Particle.global_best_position = [0] * dimensions
    Particle.INERTIA_WEIGHT = inertia_weight
    Particle.SOCIAL_CONSTANT = social_constant
    Particle.COGNITIVE_CONSTANT = cognitive_constant
    swarm = [Particle.Particle(function, dimensions, domain)
             for _ in range(population_size)]

    global_best_adaptation = np.inf
    best_positions = []
    best_adaptations = []
    for _ in range(ITERATIONS):
        for particle in swarm:
            particle.find_best_adaptation()
            particle.calculate_new_velocity()
            particle.move_to_new_position()

            if particle.best_adaptation < global_best_adaptation:
                best_positions.append(particle.best_position)
                global_best_adaptation = particle.best_adaptation
        best_adaptations.append(global_best_adaptation)

    return best_positions, best_adaptations


if __name__ == '__main__':
    best_positions, best_adaptations = particle_swarm_optimization_algorithm(
        rosenbrock, 2, 0.2, 1, 2, ROSENBROCK_DOMAIN, 100)
    print(best_positions[-1])
    print(best_adaptations[-1])
    plt.plot(np.arange(ITERATIONS), best_adaptations)
    plt.show()
