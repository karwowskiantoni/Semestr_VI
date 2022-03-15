import numpy as np
import random
from Fitness import fitness

INERTIA_WEIGHT = 1
COGNITIVE_CONSTANT = 1
SOCIAL_CONSTANT = 2


global_best_position = [0] * 100

rng = np.random.default_rng()


class Particle():
    def __init__(self, dimensions, lower_bound, upper_bound):
        self.velocity = [0] * dimensions
        self.position = random.sample(range(1, dimensions), dimensions)
        self.adaptation = 0
        self.best_position = [0] * dimensions
        self.best_adaptation = np.inf
        self.dimensions = dimensions
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound

    def calculate_inertia(self):
        return [INERTIA_WEIGHT * velocity for velocity in self.velocity]

    def calculate_cognitive_components(self):
        return [COGNITIVE_CONSTANT * rng.random() * (self.best_position[i] - self.position[i]) for i in range(self.dimensions)]

    def calculate_social_components(self):
        return [SOCIAL_CONSTANT * rng.random() * (global_best_position[i] - self.position[i]) for i in range(self.dimensions)]

    def calculate_new_velocity(self):
        inertia = self.calculate_inertia()
        cognitive = self.calculate_cognitive_components()
        social = self.calculate_social_components()
        for i in range(self.dimensions):
            self.velocity[i] = inertia[i] + cognitive[i] + social[i]

    def move_to_new_position(self):
        for i in range(self.dimensions):
            self.position[i] += self.velocity[i]
            if self.position[i] > self.upper_bound:
                self.position[i] = self.upper_bound
            elif self.position[i] < self.lower_bound:
                self.position[i] = self.lower_bound

    def find_best_adaptation(self):
        self.adaptation = fitness(self)
        if self.adaptation < self.best_adaptation:
            self.best_position = self.position.copy()
            self.best_adaptation = self.adaptation
