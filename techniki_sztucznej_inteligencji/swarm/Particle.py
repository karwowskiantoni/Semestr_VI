import numpy as np

INERTIA_WEIGHT = 1
COGNITIVE_CONSTANT = 1
SOCIAL_CONSTANT = 2
DIMENSIONS = 20


global_best_position = [0] * DIMENSIONS

rng = np.random.default_rng()


class Particle():
    def __init__(self, function, dimensions, domain):
        lower_bound = domain[0]
        upper_bound = domain[1]
        self.velocity = [0] * dimensions
        self.position = rng.uniform(lower_bound, upper_bound, dimensions)
        self.adaptation = 0
        self.best_position = [0] * dimensions
        self.best_adaptation = np.inf
        self.dimensions = dimensions
        self.lower_bound = lower_bound
        self.upper_bound = upper_bound
        self.function = function

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
        self.adaptation = self.function(self.position)
        if self.adaptation < self.best_adaptation:
            self.best_position = self.position.copy()
            self.best_adaptation = self.adaptation
