import numpy as np
from numba import njit

rng = np.random.default_rng()


class Particle:
    def __init__(self, function, dimensions, domain, inertia_weight, stopping_gap):
        lower_bound = domain[0]
        upper_bound = domain[1]
        self.velocity = np.zeros(dimensions)
        self.position = rng.uniform(lower_bound, upper_bound, dimensions)
        self.adaptation = function(self.position)
        self.best_position = np.array([0] * dimensions)
        self.best_adaptation = np.inf
        self.dimensions = dimensions
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound
        self.function = function
        self.inertia_weight = inertia_weight
        self.stopping_gap = stopping_gap
        self.no_improvement_iterations = 0
        self.offspring = np.zeros(dimensions)
        self.exemplar = self.position.copy()

    def calculate_inertia(self):
        return np.multiply(self.velocity, self.inertia_weight)

    def calculate_new_velocity(self, acceleration_coefficient):
        self.velocity = self.get_new_velocity(self.velocity, self.inertia_weight, self.dimensions,
                                              acceleration_coefficient, self.exemplar, self.position)

    @staticmethod
    @njit()
    def get_new_velocity(velocity, inertia_weight, dimensions, acceleration_coefficient, exemplar, position):
        return np.multiply(velocity, inertia_weight) \
               + np.multiply(np.random.random(size=dimensions), acceleration_coefficient) \
               * np.subtract(exemplar, position)

    def move_to_new_position(self):
        self.position = self.get_new_position(self.position, self.velocity, self.lower_bound, self.upper_bound)

    @staticmethod
    @njit()
    def get_new_position(position, velocity, lower_bound, upper_bound):
        new_position = np.add(position, velocity)
        new_position[new_position > upper_bound] = upper_bound
        new_position[new_position < lower_bound] = lower_bound
        return new_position

    def evaluate_adaptation(self):
        self.adaptation = self.function(self.position)
        exemplar_adaptation = self.function(self.exemplar)
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

    def mutate(self, mutation_probability):
        self.position = self.get_mutated_position(self.dimensions, self.lower_bound,
                                                  self.upper_bound, mutation_probability, self.position)

    @staticmethod
    @njit()
    def get_mutated_position(dimensions, lower, upper, mutation_probability, position):
        random_coefficients = np.random.random(size=dimensions)
        random_positions = np.random.uniform(lower, upper, size=dimensions)
        return np.where(random_coefficients < mutation_probability, random_positions, position)

    def selection(self, global_best_position):
        if self.function(self.offspring) < self.function(self.exemplar):
            self.exemplar = self.offspring.copy()
        else:
            self.no_improvement_iterations += 1
        if self.no_improvement_iterations >= self.stopping_gap:
            self.stopping_gap = 0
            self.position = global_best_position.copy()
