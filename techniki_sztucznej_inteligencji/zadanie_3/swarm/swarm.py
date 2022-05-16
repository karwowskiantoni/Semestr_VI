import numpy as np

from swarm.Particle import Particle


def gl_pso(
        function,
        dimensions_number,
        inertia_weight,
        mutation_probability,
        stopping_gap,
        domain,
        population_size,
        iteration_number,
        obl=False,
        de_mutation=False):
    global_best_position = [0] * dimensions_number
    swarm = np.array([Particle(dimensions_number, domain)
             for _ in range(population_size)])

    global_best_adaptation = np.inf
    lower_bound, upper_bound = domain

    for particle in swarm:
        if particle.best_adaptation < global_best_adaptation:
            global_best_position = particle.best_position.copy()
            global_best_adaptation = particle.best_adaptation

    for _ in range(iteration_number):
        for particle in swarm:
            particle.crossover(swarm, global_best_position)
            particle.mutate(swarm, mutation_probability, lower_bound, upper_bound, de_mutation)
            particle.evaluate_adaptation(function)
            particle.selection(global_best_position, stopping_gap, function, obl)

            particle.calculate_new_velocity(0.1, inertia_weight)
            particle.move_to_new_position(lower_bound, upper_bound)

            if particle.best_adaptation < global_best_adaptation:
                global_best_position = particle.best_position.copy()
                global_best_adaptation = particle.best_adaptation

    return global_best_adaptation
