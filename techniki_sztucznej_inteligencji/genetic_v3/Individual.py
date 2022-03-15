import random


class Individual:
    def __init__(self, genome_size, domain, fitness_function, amplification_factor, crossing_factor):
        self.genome = [random.uniform(domain[0], domain[1]) for _ in range(genome_size)]
        self.fitness_function = fitness_function
        self.amplification_factor = amplification_factor
        self.crossing_factor = crossing_factor
        self.domain = domain

    def offspring(self, chosen_individual, other_individuals):
        mutant = self.mutate(chosen_individual, other_individuals)
        trial = self.cross(mutant)
        self.genome = self.select(trial).genome

    def mutate(self, chosen, other_individuals):
        for i in range(len(chosen.genome)):
            chosen.genome[i] += self.amplification_factor * (other_individuals[0].genome[i] - other_individuals[1].genome[i])

            if chosen.genome[i] < self.domain[0]:
                chosen.genome[i] = self.domain[0]
            if chosen.genome[i] > self.domain[1]:
                chosen.genome[i] = self.domain[1]

        return chosen

    def cross(self, mutant):
        for i in range(len(mutant.genome)):
            if random.random() > self.crossing_factor:
                mutant.genome[i] = self.genome[i]
        return mutant

    def select(self, trial):
        if self.fitness() > trial.fitness():
            return self
        else:
            return trial

    def fitness(self):
        return self.fitness_function(self.genome)
