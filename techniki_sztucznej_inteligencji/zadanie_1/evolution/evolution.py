import random

from zadanie_1.evolution.Individual import Individual


def differential_evolution_algorithm(
        function,
        domain,
        dimensions_number,
        population_size,
        amplification_factor,
        crossing_factor,
        iteration_number=None,
        expected_fitness=None):
    population = [Individual(
        genome=[random.uniform(domain[0], domain[1]) for _ in range(dimensions_number)],
        fitness_function=function,
        amplification_factor=amplification_factor,
        crossing_factor=crossing_factor,
        domain=domain)
        for _ in range(population_size)]

    if iteration_number is not None and expected_fitness is None:

        for _ in range(iteration_number):
            for i in range(len(population)):
                population[i] = population[i].offspring(best_in_population(population), random.sample(population, 2))
        return best_in_population(population).fitness()

    elif iteration_number is None and expected_fitness is not None:

        best_fitness = best_in_population(population).fitness()
        while best_fitness > expected_fitness:
            for i in range(len(population)):
                population[i] = population[i].offspring(best_in_population(population), random.sample(population, 2))
                best_fitness = best_in_population(population).fitness()
        return best_fitness

    else:
        print("weź się kurwa chłopie na coś zdecyduj")
        return 0


def best_in_population(population):
    best_individual = population[0]
    for individual in population:
        if best_individual.fitness() > individual.fitness():
            best_individual = individual
    return best_individual
