import numpy as np

from swarm.Particle import Particle


def genetic_learning_particle_swarm_optimization_algorithm(
        function,
        dimensions_number,
        inertia_weight,
        mutation_probability,
        stopping_gap,
        domain,
        population_size,
        iteration_number):
    global_best_position = [0] * dimensions_number
    swarm = np.array([Particle(function, dimensions_number, domain, inertia_weight, stopping_gap)
             for _ in range(population_size)])

    global_best_adaptation = np.inf

    for particle in swarm:
        if particle.best_adaptation < global_best_adaptation:
            global_best_position = particle.best_position.copy()
            global_best_adaptation = particle.best_adaptation

    for _ in range(iteration_number):
        for particle in swarm:
            particle.crossover(swarm, global_best_position)
            particle.mutate(mutation_probability)
            particle.evaluate_adaptation()
            particle.selection(global_best_position)

            particle.calculate_new_velocity(0.1)
            particle.move_to_new_position()

            if particle.best_adaptation < global_best_adaptation:
                global_best_position = particle.best_position.copy()
                global_best_adaptation = particle.best_adaptation

    return global_best_adaptation
