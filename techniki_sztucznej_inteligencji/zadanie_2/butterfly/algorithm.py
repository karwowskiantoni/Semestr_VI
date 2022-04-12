import random

import numpy as np

from zadanie_2.butterfly.Butterfly import Butterfly


def butterfly_optimization_algorithm(
        function,
        domain,
        dimensions_number,
        population_size,
        sensor_modality,
        intensity_index,
        probability,
        iteration_number=None,
        expected_fitness=None,
        customization=False):
    population = [Butterfly(
        position=[random.uniform(domain[0], domain[1]) for _ in range(dimensions_number)],
        fitness_function=function,
        sensor_modality=sensor_modality,
        intensity_index=intensity_index,
        probability=probability,
        domain=domain)
        for _ in range(population_size)]

    if iteration_number is not None and expected_fitness is None:

        for _ in range(iteration_number):
            best = best_in_population(population)
            for i in range(len(population)):
                if customization:
                    population[i] = population[i].move(best, random.sample(population, 2), True)
                else:
                    population[i] = population[i].move(best, random.sample(population, 2), False)
        return best.fitness()

    elif iteration_number is None and expected_fitness is not None:

        best = best_in_population(population)
        while best.fitness() > expected_fitness:
            for i in range(len(population)):
                population[i] = population[i].move(best, random.sample(population, 2), False)
            best = best_in_population(population)
        return best.fitness()

    else:
        print("wrong stop condition")
        return 0


def best_in_population(population):
    best_individual = population[0]
    for individual in population:
        if best_individual.fitness() > individual.fitness():
            best_individual = individual
    return best_individual
