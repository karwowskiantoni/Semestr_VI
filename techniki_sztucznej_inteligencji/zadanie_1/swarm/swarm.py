import numpy as np

from zadanie_1.swarm.Particle import Particle


def particle_swarm_optimization_algorithm(
        function,
        dimensions_number,
        iteration_number,
        inertia_weight,
        cognitive_constant,
        social_constant,
        domain,
        population_size):
    global_best_position = [0] * dimensions_number
    swarm = [Particle(function, dimensions_number, domain, inertia_weight, cognitive_constant, social_constant)
             for _ in range(population_size)]

    global_best_adaptation = np.inf
    best_positions = []
    best_adaptations = []
    for _ in range(iteration_number):
        for particle in swarm:
            particle.find_best_adaptation()
            particle.calculate_new_velocity(global_best_position)
            particle.move_to_new_position()

            if particle.best_adaptation < global_best_adaptation:
                best_positions.append(particle.best_position)
                global_best_adaptation = particle.best_adaptation
        global_best_position = best_positions[-1]
        best_adaptations.append(global_best_adaptation)

    return best_adaptations[-1]
