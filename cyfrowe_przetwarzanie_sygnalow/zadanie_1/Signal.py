import json as js
import matplotlib.pyplot as plt
import numpy as np

from Parameters import Parameters


class Signal:
    def __init__(self, parameters, ticks, type):
        self.parameters = parameters
        self.ticks = ticks
        self.type = type

    @staticmethod
    def generate(function, parameters):
        return Signal(parameters,
                      [function(parameters, x / parameters.f) for x
                      in range(int(parameters.d - parameters.t1) * parameters.f)],
                      function.__name__)

    def sum(self, signal):
        ticks = []
        for i in range(min(len(self.ticks), len(signal.ticks))):
            ticks.append(self.ticks[i] + signal.ticks[i])
        return Signal(self.parameters, ticks, self.type + " sum " + signal.type)

    def difference(self, signal):
        ticks = []
        for i in range(min(len(self.ticks), len(signal.ticks))):
            ticks.append(self.ticks[i] - signal.ticks[i])
        return Signal(self.parameters, ticks, self.type + " difference " + signal.type)

    def product(self, signal):
        ticks = []
        for i in range(min(len(self.ticks), len(signal.ticks))):
            ticks.append(self.ticks[i] * signal.ticks[i])
        return Signal(self.parameters, ticks, self.type + " product " + signal.type)

    def divide(self, signal):
        ticks = []
        for i in range(min(len(self.ticks), len(signal.ticks))):
            if signal.ticks[i] == 0:
                ticks.append(self.ticks[i])
            else:
                ticks.append(self.ticks[i] / signal.ticks[i])
        return Signal(self.parameters, ticks, self.type + " divide " + signal.type)

    def print_plot(self, linear=True):
        plt.figure().suptitle(self.type)
        if linear:
            plt.plot([i / self.parameters.f for i in range(len(self.ticks))], self.ticks)
        else:
            plt.scatter([i / self.parameters.f for i in range(len(self.ticks))], self.ticks, s=1)
        plt.show()
        return self

    def print_histogram(self, divisions_number):
        plt.hist(self.ticks, bins=divisions_number)
        plt.show()
        return self

    def print_stats(self):
        print_yellow("----------------stats-----------------")
        print_yellow("| average: " + str(self.average()))
        print_yellow("| absolute average: " + str(self.absolute_average()))
        print_yellow("| average power: " + str(self.average_power()))
        print_yellow("| variance: " + str(self.variance()))
        print_yellow("| root_mean_square: " + str(self.root_mean_square()))
        print_yellow("--------------------------------------")
        return self

    def average(self):
        value = (1 / self.parameters.d) * np.sum(
            self.ticks) * self.parameters.d / len(self.ticks)
        return value

    def absolute_average(self):
        value = (1 / self.parameters.d) * np.sum(
            np.absolute(self.ticks)
        ) * self.parameters.d / len(self.ticks)
        return value

    def average_power(self):
        value = (1 / self.parameters.d) * np.sum(
            np.power(self.ticks, 2)
        ) * self.parameters.d / len(self.ticks)
        return value

    def variance(self):
        avg_signal_val = self.average()
        value = (1 / self.parameters.d) * np.sum(
            np.power(np.subtract(self.ticks, avg_signal_val), 2)
        ) * self.parameters.d / len(self.ticks)
        return value

    def root_mean_square(self):
        value = np.sqrt(self.average_power())
        return value

    def serialize(self, filename):
        with open(filename + ".signal", "w") as file:
            file.write(js.dumps(self, default=lambda o: o.__dict__, sort_keys=True, indent=4))
        return self

    @staticmethod
    def deserialize(filename):
        with open(filename + ".signal", "r") as file:
            dictionary = js.loads(file.read())
            signal = Signal(
                Parameters(
                    A=dictionary['parameters']['A'],
                    T=dictionary['parameters']['T'],
                    t1=dictionary['parameters']['t1'],
                    d=dictionary['parameters']['d'],
                    kw=dictionary['parameters']['kw'],
                    ts=dictionary['parameters']['ts'],
                    f=dictionary['parameters']['f'],
                    p=dictionary['parameters']['p'],
                    n1=dictionary['parameters']['n1'],
                    ns=dictionary['parameters']['ns']
                ),
                dictionary['ticks'],
                dictionary['type'])
            return signal


def print_yellow(text):
    print('\033[93m' + text + '\033[0m')
