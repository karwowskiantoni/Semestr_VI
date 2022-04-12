import random


class Butterfly:
    def __init__(self, position, fitness_function, sensor_modality, intensity_index, probability, domain):
        self.position = position
        self.fitness_function = fitness_function
        self.sensor_modality = sensor_modality
        self.intensity_index = intensity_index
        self.probability = probability
        self.domain = domain

    def copy(self):
        return Butterfly(self.position.copy(), self.fitness_function, self.sensor_modality, self.intensity_index, self.probability, self.domain)

    def move(self, best, other_two, customization):
        r = random.random()
        if r < self.probability:
            return self.__move_to_best(best, r)
        else:
            return self.__move_random(other_two, r)

    def fitness(self):
        return self.fitness_function(self.position)

    def __move_to_best(self, best, r):
        new_butterfly = self.copy()
        for i in range(len(self.position)):
            new_butterfly.position[i] = self.__bound_with_domain(self.position[i] + (r ** 2 * best.position[i] - self.position[i]) * self.__smell())
        return new_butterfly

    def __move_random(self, other_two, r):
        new_butterfly = self.copy()
        for i in range(len(self.position)):
            new_butterfly.position[i] = self.__bound_with_domain(self.position[i] + (r ** 2 * other_two[0].position[i] - other_two[1].position[i]) * self.__smell())
        return new_butterfly

    def __smell(self):
        return self.sensor_modality * (self.fitness() ** self.intensity_index)

    def __bound_with_domain(self, value):
        if value < self.domain[0]:
            return self.domain[0]
        elif value > self.domain[1]:
            return self.domain[1]
        else:
            return value





