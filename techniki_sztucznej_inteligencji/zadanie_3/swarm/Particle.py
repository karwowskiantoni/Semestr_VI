import numpy as np
from numba import njit

rng = np.random.default_rng()


class Particle:
    def __init__(self, dimensions, domain):
        lower_bound = domain[0]
        upper_bound = domain[1]
        self.velocity = np.zeros(dimensions)
        self.position = rng.uniform(lower_bound, upper_bound, dimensions)
        self.best_position = np.array([0] * dimensions)
        self.adaptation = np.inf
        self.best_adaptation = np.inf
        self.dimensions = dimensions
        self.no_improvement_iterations = 0
        self.offspring = np.zeros(dimensions)
        self.exemplar = self.position.copy()

    def calculate_new_velocity(self, acceleration_coefficient, inertia_weight):
        self.velocity = self.get_new_velocity(self.velocity, inertia_weight, self.dimensions,
                                              acceleration_coefficient, self.exemplar, self.position)

    @staticmethod
    @njit()
    def get_new_velocity(velocity, inertia_weight, dimensions, acceleration_coefficient, exemplar, position):
        return np.multiply(velocity, inertia_weight) \
               + np.multiply(np.random.random(size=dimensions), acceleration_coefficient) \
               * np.subtract(exemplar, position)

    def move_to_new_position(self, lower_bound, upper_bound):
        self.position = self.get_new_position(self.position, self.velocity, lower_bound, upper_bound)

    @staticmethod
    @njit()
    def get_new_position(position, velocity, lower_bound, upper_bound):
        new_position = np.add(position, velocity)
        new_position[new_position > upper_bound] = upper_bound
        new_position[new_position < lower_bound] = lower_bound
        return new_position

    def evaluate_adaptation(self, function):
        self.adaptation = function(self.position)
        exemplar_adaptation = function(self.exemplar)
        if exemplar_adaptation < self.adaptation:
            self.adaptation = exemplar_adaptation
            self.position = self.exemplar
        if self.adaptation < self.best_adaptation:
            self.best_position = self.position
            self.best_adaptation = self.adaptation

    def crossover(self, swarm, global_best_position):
        partner_indices = self.get_random_partner_indices(len(swarm), self.dimensions)
        randoms = self.get_random_coefficients(self.dimensions)
        partners = swarm[partner_indices]
        for d, partner in zip(range(self.dimensions), partners):
            if self.adaptation < partner.adaptation:
                self.offspring[d] = randoms[d][0] * self.best_position[d] + (1 - randoms[d][1]) * global_best_position[d]
            else:
                self.offspring[d] = partner.position[d]

    @staticmethod
    @njit()
    def get_random_partner_indices(swarm_len, dimensions):
        return np.random.randint(0, swarm_len, size=dimensions)

    @staticmethod
    @njit()
    def get_random_coefficients(dimensions):
        return np.random.random(size=(dimensions, 2))

    def mutate(self, swarm, mutation_probability, lower_bound, upper_bound, de_mutation=False):
        if de_mutation:
            first_partner, second_partner = self.get_random_partner_indices(len(swarm), 2)
            self.position = self.get_de_mutated_position(lower_bound, upper_bound,
                                                         mutation_probability, self.position,
                                                         swarm[first_partner].position, swarm[second_partner].position)
        else:
            self.position = self.get_mutated_position(self.dimensions, lower_bound, upper_bound, mutation_probability,
                                                      self.position)

    @staticmethod
    @njit()
    def get_mutated_position(dimensions, lower, upper, mutation_probability, position):
        random_coefficients = np.random.random(size=dimensions)
        random_positions = np.random.uniform(lower, upper, size=dimensions)
        return np.where(random_coefficients < mutation_probability, random_positions, position)

    @staticmethod
    @njit()
    def get_de_mutated_position(lower, upper, amplification_factor, position, first_partner, second_partner):
        mutant = position + np.multiply(amplification_factor, first_partner - second_partner)
        mutant[mutant > upper] = upper
        mutant[mutant < lower] = lower
        return mutant


    def selection(self, global_best_position, stopping_gap, function, obl=False):
        if function(self.offspring) < function(self.exemplar):
            self.exemplar = self.offspring.copy()
        else:
            flipped_offspring = np.multiply(self.offspring, -1)
            if obl and function(flipped_offspring) < function(self.offspring):
                self.offspring = flipped_offspring
            self.no_improvement_iterations += 1
        if self.no_improvement_iterations >= stopping_gap:
            self.no_improvement_iterations = 0
            self.position = global_best_position.copy()
