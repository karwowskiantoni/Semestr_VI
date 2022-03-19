import random

from zadanie_1.evolution.Individual import Individual


def differential_evolution_algorithm(
        function,
        domain,
        dimensions_number,
        population_size,
        iteration_number,
        amplification_factor,
        crossing_factor):
    population = [Individual(
        genome=[random.uniform(domain[0], domain[1]) for _ in range(dimensions_number)],
        fitness_function=function,
        amplification_factor=amplification_factor,
        crossing_factor=crossing_factor,
        domain=domain)
        for _ in range(population_size)]

    for _ in range(iteration_number):
        for i in range(len(population)):
            population[i] = population[i].offspring(best_in_population(population), random.sample(population, 2))

    return best_in_population(population).fitness()


def best_in_population(population):
    best_individual = population[0]
    for individual in population:
        if best_individual.fitness() > individual.fitness():
            best_individual = individual
    return best_individual
