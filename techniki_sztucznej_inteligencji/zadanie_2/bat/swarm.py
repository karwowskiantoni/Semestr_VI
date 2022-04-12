import numpy as np

from Bat import Bat


def bat_optimization_algorithm(
        function,
        dimensions,
        domain,
        frequency_bounds,
        pulse_rate,
        pulse_rate_multiplier,
        loudness,
        loudness_multiplier,
        population_size,
        iteration_number=None,
        expected_fitness=None,
        customization=False):
    global_best_position = [0] * dimensions
    swarm = [Bat(function, dimensions, domain, frequency_bounds,
                 pulse_rate, pulse_rate_multiplier, loudness, loudness_multiplier, customization)
             for _ in range(population_size)]
    if iteration_number is not None and expected_fitness is None:
        global_best_position = swarm[0].position
        global_best_adaptation = np.inf
        for i in range(iteration_number):
            average_loudness = np.average(
                list(map(lambda x: x.loudness, swarm)))
            for bat in swarm:
                bat.move(global_best_position, average_loudness)

                if bat.adaptation < global_best_adaptation:
                    global_best_position = bat.position.copy()
                    global_best_adaptation = bat.adaptation
    elif expected_fitness is not None:
        global_best_position = swarm[0].position
        average_loudness = np.average(
            list(map(lambda x: x.loudness, swarm)))
        for bat in swarm:
            bat.move(global_best_position, average_loudness)

            if bat.adaptation < global_best_adaptation:
                global_best_position = bat.position.copy()
                global_best_adaptation = bat.adaptation
        while global_best_adaptation > expected_fitness:
            average_loudness = np.average(
                list(map(lambda x: x.loudness, swarm)))
            for bat in swarm:
                bat.move(global_best_position, average_loudness)

                if bat.adaptation < global_best_adaptation:
                    global_best_position = bat.position.copy()
                    global_best_adaptation = bat.adaptation

    return global_best_adaptation


if __name__ == "__main__":
    def sphere(params):
        return sum([x**2 for x in params])

    def rosenbrock(params):
        return sum([100 * (params[i + 1] - params[i]**2)**2 + (params[i] - 1)**2 for i in range(len(params) - 1)])
    SPHERE_DOMAIN = tuple((-100, 100))
    ROSENBROCK_DOMAIN = tuple((-2.048, 2.048))

    result = bat_optimization_algorithm(
        function=rosenbrock,
        dimensions=10,
        domain=ROSENBROCK_DOMAIN,
        frequency_bounds=tuple((0.0, 2.0)),
        pulse_rate=0.5,
        pulse_rate_multiplier=0.1,
        loudness=0.5,
        loudness_multiplier=0.1,
        population_size=100,
        iteration_number=100,
        customization=True)

    print(result)
