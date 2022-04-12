import numpy as np
import math

rng = np.random.default_rng()


class Bat:
    def __init__(self, function, dimensions, domain, frequency_bounds, pulse_rate, pulse_rate_multiplier, loudness, loudness_multiplier, customization):
        self.function = function
        self.velocity = [0] * dimensions
        self.lower_position_bound = domain[0]
        self.upper_position_bound = domain[1]
        self.lower_frequency_bound = frequency_bounds[0]
        self.upper_frequency_bound = frequency_bounds[1]
        self.position = rng.uniform(
            self.lower_position_bound, self.upper_position_bound, dimensions)
        self.adaptation = np.inf
        self.dimensions = dimensions
        self.initial_pulse_rate = pulse_rate
        self.pulse_rate = pulse_rate
        self.pulse_rate_multiplier = pulse_rate_multiplier
        self.loudness = loudness
        self.loudness_multiplier = loudness_multiplier
        self.frequency = self.initialize_frequency()
        self.current_iteration = 1
        self.customization = customization

    def initialize_frequency(self):
        return self.lower_frequency_bound + (self.upper_frequency_bound - self.lower_frequency_bound) * rng.random()

    def move(self, global_best_position, average_loudness):
        self.frequency = self.initialize_frequency()
        position_copy = self.position.copy()
        for i in range(self.dimensions):
            self.velocity[i] = (global_best_position[i] -
                                self.position[i]) * self.frequency
            # self.velocity[i] += (self.position[i] -
            #                      global_best_position[i]) * self.frequency
            self.position[i] += self.velocity[i]
            self.adjust_to_bounds(i)

        for i in range(self.dimensions):
            if rng.random() < self.loudness:
                self.position[i] += (2 * rng.random() - 1) * average_loudness
                self.adjust_to_bounds(i)

        new_adaptation = self.function(self.position)

        if new_adaptation < self.adaptation and rng.random() < self.pulse_rate:
            self.adaptation = new_adaptation
            self.loudness *= self.loudness_multiplier
            self.pulse_rate = self.initial_pulse_rate * \
                (1 - math.exp(-self.pulse_rate_multiplier * self.current_iteration))
            if self.customization and self.loudness < 0.05:
                self.loudness = 0.05
        else:
            self.position = position_copy
        self.current_iteration += 1

    def adjust_to_bounds(self, i):
        if self.position[i] < self.lower_position_bound:
            self.position[i] = self.lower_position_bound
        if self.position[i] > self.upper_position_bound:
            self.position[i] = self.upper_position_bound
