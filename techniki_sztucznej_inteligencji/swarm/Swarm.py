import sys
sys.path.append("..")

import numpy as np
import Particle
from functions import *
import matplotlib.pyplot as plt


ITERATIONS = 100


def generate_swarm(function, dimensions, domain, population_size):
    Particle.DIMENSIONS = dimensions
    return [Particle.Particle(function, dimensions, domain) for _ in range(population_size)]


def find_minimum(swarm):
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
    swarm = generate_swarm(sphere, 20, SPHERE_DOMAIN, 100)
    best_positions, best_adaptations = find_minimum(swarm)
    print(best_positions)
    plt.plot(np.arange(ITERATIONS), best_adaptations)
    plt.show()
