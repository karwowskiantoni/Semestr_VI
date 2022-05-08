import json as js
import matplotlib.pyplot as plt
import numpy as np

from math import log10
from Parameters import Parameters


class Signal:
    def __init__(self, parameters, samples, type):
        self.parameters = parameters
        self.samples = samples
        self.type = type

    @staticmethod
    def generate(function, parameters):
        samples = Signal.sample(function, parameters)
        return Signal(parameters, samples, function.__name__)

    @staticmethod
    def sample(function, parameters):
        return [function(parameters, x / parameters.f) for x in range(int((parameters.d - parameters.t1) * parameters.f))]

    def quantize_flat(self, level):
        step = (max(self.samples) - min(self.samples)) / level
        samples = step * np.floor(np.array(self.samples)/step)
        return Signal(self.parameters, list(samples), self.type + "_quantized_flat_" + str(level))

    def quantize_round(self, level):
        step = (max(self.samples) - min(self.samples)) / level
        samples = step * np.round(np.array(self.samples)/step)
        return Signal(self.parameters, list(samples), self.type + "_quantized_round_" + str(level))

    def interpolate_zero(self, samples_number):
        return self

    def interpolate_first(self, samples_number):
        return self

    def interpolate_sin(self, samples_number):
        return self

    def first_order_hold(self, x):
        pass

    def zero_order_hold(self, x):
        pass

    def sinc(self, x):
        pass

    def sum(self, signal):
        samples = []
        for i in range(min(len(self.samples), len(signal.samples))):
            samples.append(self.samples[i] + signal.samples[i])
        return Signal(self.parameters, samples, self.type + "_sum_" + signal.type)

    def difference(self, signal):
        samples = []
        for i in range(min(len(self.samples), len(signal.samples))):
            samples.append(self.samples[i] - signal.samples[i])
        return Signal(self.parameters, samples, self.type + "_difference_" + signal.type)

    def product(self, signal):
        samples = []
        for i in range(min(len(self.samples), len(signal.samples))):
            samples.append(self.samples[i] * signal.samples[i])
        return Signal(self.parameters, samples, self.type + "_product_" + signal.type)

    def divide(self, signal):
        samples = []
        for i in range(min(len(self.samples), len(signal.samples))):
            if signal.samples[i] == 0:
                samples.append(self.samples[i])
            else:
                samples.append(self.samples[i] / signal.samples[i])
        return Signal(self.parameters, samples, self.type + "_divide_" + signal.type)

    def print_plot(self, signal=None):
        plt.figure().suptitle(self.type)
        plt.plot([i / self.parameters.f for i in range(len(self.samples))], self.samples)
        if signal is not None:
            plt.plot([i / signal.parameters.f for i in range(len(signal.samples))], signal.samples)

        plt.savefig("plots/" + self.type)
        plt.show()
        return self

    def print_histogram(self, divisions_number):
        plt.hist(self.samples, bins=divisions_number)
        plt.savefig("hists/" + self.type)
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
            self.samples) * self.parameters.d / len(self.samples)
        return value

    def absolute_average(self):
        value = (1 / self.parameters.d) * np.sum(
            np.absolute(self.samples)
        ) * self.parameters.d / len(self.samples)
        return value

    def average_power(self):
        value = (1 / self.parameters.d) * np.sum(
            np.power(self.samples, 2)
        ) * self.parameters.d / len(self.samples)
        return value

    def variance(self):
        avg_signal_val = self.average()
        value = (1 / self.parameters.d) * np.sum(
            np.power(np.subtract(self.samples, avg_signal_val), 2)
        ) * self.parameters.d / len(self.samples)
        return value

    def root_mean_square(self):
        value = np.sqrt(self.average_power())
        return value

    def print_comparison(self, signal):
        if len(signal.samples) != len(self.samples):
            print_yellow("signals with different sampling, cannot compare")
            return self
        print_yellow("--------------comparison--------------")
        print_yellow("| mean square error: " + str(self.mean_square_error(signal)))
        print_yellow("| signal to noise ratio: " + str(self.signal_to_noise_ratio(signal)))
        print_yellow("| max signal to noise ratio: " + str(self.max_signal_to_noise_ratio(signal)))
        print_yellow("| max difference: " + str(self.max_difference(signal)))
        print_yellow("--------------------------------------")
        return self

    def mean_square_error(self, signal):
        return sum([pow(self.samples[i] - signal.samples[i], 2) for i in range(len(self.samples))]) / len(self.samples)

    def signal_to_noise_ratio(self, signal):
        return 10 * log10(sum([pow(i, 2) for i in self.samples]) / sum([pow(self.samples[i] - signal.samples[i], 2) for i in range(len(self.samples))]))

    def max_signal_to_noise_ratio(self, signal):
        return 10 * log10(max(self.samples) / self.mean_square_error(signal))

    def max_difference(self, signal):
        return max([abs(self.samples[i] - signal.samples[i]) for i in range(len(self.samples))])

    def serialize(self, filename=None):
        if filename is None:
            filename = self.type
        with open("signals/" + filename + ".signal", "w") as file:
            file.write(js.dumps(self, default=lambda o: o.__dict__, sort_keys=True, indent=4))
        return self

    @staticmethod
    def deserialize(filename):
        with open("signals/" + filename + ".signal", "r") as file:
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
                dictionary['samples'],
                dictionary['type'])
            return signal


def print_yellow(text):
    print('\033[93m' + text + '\033[0m')

# if self.type == "unit_impulse" or self.type == "noise_impulse":
#     plt.scatter([i / self.parameters.f for i in range(len(self.samples))], self.samples, s=1)
# else:
