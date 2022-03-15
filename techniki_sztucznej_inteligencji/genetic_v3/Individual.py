import random


class Individual:
    def __init__(self, genome_size, domain):
        self.genome = [random.uniform(domain[0], domain[1]) for i in range(genome_size)]

    def generate_offspring(self, chosen, other_individuals, amplification_factor):
        mutant = self.mutate( , ther_individuals, amplification_factor)
        trial = cross()

    def mutate(self, other_individuals, amplification_factor):
        for i in range(len(self.genome)):
            self.genome[i] =
