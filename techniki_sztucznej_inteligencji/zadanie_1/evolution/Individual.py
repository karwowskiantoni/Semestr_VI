import random


class Individual:
    def __init__(self, genome, fitness_function, amplification_factor, crossing_factor, domain):
        self.genome = genome
        self.fitness_function = fitness_function
        self.amplification_factor = amplification_factor
        self.crossing_factor = crossing_factor
        self.domain = domain

    def copy(self):
        return Individual(self.genome.copy(), self.fitness_function, self.amplification_factor, self.crossing_factor, self.domain)

    def offspring(self, chosen_individual, other_individuals):
        mutant = self.mutate(chosen_individual, other_individuals)
        trial = self.cross(mutant)
        return self.select(trial)

    def mutate(self, chosen, other_individuals):
        mutant = chosen.copy()
        for i in range(len(chosen.position)):
            mutant.position[i] += self.amplification_factor * (other_individuals[0].position[i] - other_individuals[1].position[i])

            if mutant.position[i] < self.domain[0]:
                mutant.position[i] = self.domain[0]
            if mutant.position[i] > self.domain[1]:
                mutant.position[i] = self.domain[1]

        return mutant

    def cross(self, mutant):
        trial = mutant.copy()
        for i in range(len(trial.position)):
            if random.random() <= self.crossing_factor:
                trial.position[i] = self.genome[i]
        return trial

    def select(self, trial):
        if self.fitness() < trial.fitness():
            return self
        else:
            return trial

    def fitness(self):
        return self.fitness_function(self.genome)
