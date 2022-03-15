from random import sample

from genetic_v3.Individual import Individual


def differential_evolution_algorithm(function,
                                     domain,
                                     dimensions_number,
                                     population_size,
                                     stop_condition,
                                     params):
    population = [Individual(
        dimensions_number,
        domain,
        function,
        params[0],
        params[1])
        for _ in range(population_size)]

    for _ in range(30):
        for individual in population:
            individual.offspring(best_in_population(population), sample(population, 2))
        print(best_in_population(population).fitness())

    return best_in_population(population).genome


def best_in_population(population):
    best_individual = population[0]
    for individual in population:
        if best_individual.fitness() < individual.fitness():
            best_individual = individual
    return best_individual

