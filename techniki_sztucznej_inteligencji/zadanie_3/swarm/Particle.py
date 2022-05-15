import numpy as np
from random import sample

rng = np.random.default_rng()


class Particle:
    def __init__(self, function, dimensions, domain, inertia_weight, cognitive_constant, social_constant, stopping_gap):
        lower_bound = domain[0]
        upper_bound = domain[1]
        self.velocity = [0] * dimensions
        self.position = rng.uniform(lower_bound, upper_bound, dimensions)
        self.adaptation = function(self.position)
        self.best_position = [0] * dimensions
        self.best_adaptation = np.inf
        self.dimensions = dimensions
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound
        self.function = function
        self.inertia_weight = inertia_weight
        self.cognitive_constant = cognitive_constant
        self.social_constant = social_constant
        self.stopping_gap = stopping_gap
        self.no_improvement_iterations = 0
        self.offspring = [0] * dimensions
        self.exemplar = self.position.copy()

    def calculate_inertia(self):
        return [(self.inertia_weight * velocity) for velocity in self.velocity]

    def calculate_cognitive_components(self):
        return [self.cognitive_constant * rng.random() * (self.best_position[i] - self.position[i]) for i in range(self.dimensions)]

    def calculate_social_components(self, global_best_position):
        return [self.social_constant * rng.random() * (global_best_position[i] - self.position[i]) for i in range(self.dimensions)]

    def calculate_new_velocity(self, global_best_position):
        inertia = self.calculate_inertia()
        cognitive = self.calculate_cognitive_components()
        social = self.calculate_social_components(global_best_position)
        for i in range(self.dimensions):
            self.velocity[i] = inertia[i] + cognitive[i] + social[i]

    def calculate_new_velocity_genetic(self, acceleration_coefficient):
        inertia = self.calculate_inertia()
        for i in range(self.dimensions):
            self.velocity[i] = inertia[i] + acceleration_coefficient * rng.random() * (self.exemplar[i] - self.position[i])

    def move_to_new_position(self):
        for i in range(self.dimensions):
            self.position[i] += self.velocity[i]
            if self.position[i] > self.upper_bound:
                self.position[i] = self.upper_bound
            elif self.position[i] < self.lower_bound:
                self.position[i] = self.lower_bound

    def evaluate_adaptation(self):
        self.adaptation = self.function(self.position)
        exemplar_adaptation = self.function(self.exemplar)
        if exemplar_adaptation < self.adaptation:
            self.adaptation = exemplar_adaptation
            self.position = self.exemplar.copy()
        if self.adaptation < self.best_adaptation:
            self.best_position = self.position.copy()
            self.best_adaptation = self.adaptation

    def crossover(self, swarm, global_best_position):
        for d in range(self.dimensions):
            partner = sample(swarm, 1)[0]
            if self.adaptation < partner.adaptation:
                self.offspring[d] = rng.random() * self.best_position[d] + (1 - rng.random()) * global_best_position[d]
            else:
                self.offspring[d] = partner.position[d]

    def mutate(self, mutation_probability):
        for d in range(self.dimensions):
            if rng.random() < mutation_probability:
                self.position[d] = rng.uniform(self.lower_bound, self.upper_bound)

    def selection(self, swarm):
        if self.function(self.offspring) < self.function(self.exemplar):
            self.exemplar = self.offspring.copy()
        else:
            self.no_improvement_iterations += 1
        if self.no_improvement_iterations >= self.stopping_gap:
            self.stopping_gap = 0
