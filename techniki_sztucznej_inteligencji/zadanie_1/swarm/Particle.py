import numpy as np

rng = np.random.default_rng()


class Particle:
    def __init__(self, function, dimensions, domain, inertia_weight, cognitive_constant, social_constant):
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
        self.inertia_weight = inertia_weight
        self.cognitive_constant = cognitive_constant
        self.social_constant = social_constant

    def calculate_inertia(self):
        return [self.inertia_weight * velocity for velocity in self.velocity]

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
