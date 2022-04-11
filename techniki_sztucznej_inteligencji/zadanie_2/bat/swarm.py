import numpy as np

from zadanie_1.swarm.Particle import Particle


def particle_swarm_optimization_algorithm(
        function,
        dimensions_number,
        inertia_weight,
        cognitive_constant,
        social_constant,
        domain,
        population_size,
        iteration_number=None,
        expected_fitness=None,
        customization=False):
    global_best_position = [0] * dimensions_number
    swarm = [Particle(function, dimensions_number, domain, inertia_weight, cognitive_constant, social_constant, iteration_number, customization)
             for _ in range(population_size)]

    global_best_adaptation = np.inf
    best_positions = []
    best_adaptations = []
    if iteration_number is not None and expected_fitness is None:
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
    elif expected_fitness is not None:
        for particle in swarm:
            particle.find_best_adaptation()
            particle.calculate_new_velocity(global_best_position)
            particle.move_to_new_position()

            if particle.best_adaptation < global_best_adaptation:
                best_positions.append(particle.best_position)
                global_best_adaptation = particle.best_adaptation
        global_best_position = best_positions[-1]
        best_adaptations.append(global_best_adaptation)
        while best_adaptations[-1] > expected_fitness:
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
