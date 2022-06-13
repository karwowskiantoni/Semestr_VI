import json as js

import matplotlib.pyplot as plt
import numpy as np

from math import log10
from Parameters import Parameters
from functions import impulse_response, low_pass_to_mid_pass, blackman_window


class Signal:
    def __init__(self, parameters, samples, type):
        self.parameters = parameters
        self.samples = samples
        self.type = type

    def copy(self):
        return Signal(self.parameters.copy(), self.samples.copy(), self.type)

    @staticmethod
    def generate(function, parameters):
        samples = Signal.sample(function, parameters)
        return Signal(parameters, samples, function.__name__)

    @staticmethod
    def sample(function, parameters):
        return [function(parameters, x / parameters.sampling_f) for x in
                range(int((parameters.d - parameters.t1) * parameters.sampling_f))]

    def convolve(self, samples):
        return Signal(self.parameters, np.convolve(self.samples, samples).tolist(), self.type + " convolved")

    def correlate(self, signal):
        return Signal(self.parameters, np.convolve(self.samples, signal.samples[::-1]).tolist(),
                      self.type + " correlated with " + signal.type)

    def delta_time_in_correlation(self):
        second_half = self.samples[int(len(self.samples)/2):]
        max_value = max(second_half)
        index = second_half.index(max_value)
        return index / self.parameters.sampling_f

    def filter(self, M, cutoff_f, window, mode):
        response = [impulse_response(M, cutoff_f, self.parameters.sampling_f, x) * window(x) * mode(x) for x in range(int(M) - 1)]

        plt.figure().suptitle("impulse response")
        plt.plot([i for i in range(int(M) - 1)], response)
        plt.show()

        to_cut = int(len(response)/2)
        print(to_cut)
        samples = np.convolve(self.samples, response).tolist()
        samples = samples[to_cut:len(samples) - to_cut]
        return Signal(self.parameters, samples, self.type + " filtered")

    def filter_low_pass_rectangular(self, M, cutoff_f):
        return self.filter(M, cutoff_f, lambda x: 1, lambda x: 1)

    def filter_mid_pass_rectangular(self, M, cutoff_f):
        return self.filter(M, cutoff_f, lambda x: 1, low_pass_to_mid_pass)

    def filter_low_pass_blackman(self, M, cutoff_f):
        return self.filter(M, cutoff_f, lambda x: blackman_window(M, x), lambda x: 1)

    def filter_mid_pass_blackman(self, M, cutoff_f):
        return self.filter(M, cutoff_f, lambda x: blackman_window(M, x), low_pass_to_mid_pass)

    def rotate_seconds(self, seconds):
        signal = self.copy()
        times = signal.parameters.sampling_f * seconds
        signal.rotate_times(int(round(times, 0)))
        return signal

    def rotate_times(self, times):
        for _ in range(times):
            self.rotate_once()

    def rotate_once(self):
        g = self.samples[0]
        self.samples.pop(0)
        self.samples.append(g)

    @staticmethod
    def quantize_flat(self, level):
        step = (max(self.samples) - min(self.samples)) / level
        samples = step * np.floor(np.array(self.samples) / step)
        return Signal(self.parameters, list(samples), self.type + "_quantized_flat_" + str(level))

    def quantize_round(self, level):
        step = (max(self.samples) - min(self.samples)) / level
        samples = step * np.round(np.array(self.samples) / step)
        return Signal(self.parameters, list(samples), self.type + "_quantized_round_" + str(level))

    def interpolate_zero(self, new_frequency):
        parameters = self.parameters.copy()
        parameters.sampling_f = new_frequency
        return Signal(parameters, Signal.sample(self.zero_order_hold, parameters), "zero_order_hold_" + self.type)

    def interpolate_first(self, new_frequency):
        parameters = self.parameters.copy()
        parameters.sampling_f = new_frequency
        return Signal(parameters, Signal.sample(self.first_order_hold, parameters), "first_order_hold_" + self.type)

    def interpolate_sin(self, new_frequency):
        parameters = self.parameters.copy()
        parameters.sampling_f = new_frequency
        return Signal(parameters, Signal.sample(self.interpolate_sinc, parameters), "interpolate_sinc_" + self.type)

    def zero_order_hold(self, params, t):
        T = 1 / self.parameters.sampling_f
        return sum([self.samples[i] * self.rect((t - (T / 2) - (i * T)) / T) for i in range(len(self.samples))])

    def first_order_hold(self, params, t):
        T = 1 / self.parameters.sampling_f
        return sum([self.samples[i] * self.tri((t - (i * T)) / T) for i in range(len(self.samples))])

    def interpolate_sinc(self, params, t):
        T = 1 / self.parameters.sampling_f
        return sum([self.samples[i] * self.sinc((t / T) - i) for i in range(len(self.samples))])

    def rect(self, t):
        if abs(t) > 1 / 2:
            return 0
        elif abs(t) == 1 / 2:
            return 1 / 2
        else:
            return 1

    def tri(self, x):
        return max(1 - abs(x), 0)

    def sinc(self, x):
        if x == 0:
            return 1
        else:
            return np.sin(np.pi * x) / (np.pi * x)

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

    def X(self):
        return [i / self.parameters.sampling_f for i in range(len(self.samples))]

    def print_plot(self, signal=None):
        plt.figure().suptitle(self.type)
        plt.plot(self.X(), self.samples)
        if signal is not None:
            plt.plot(signal.X(), signal.samples)

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
        return 10 * log10(sum([pow(i, 2) for i in self.samples]) / sum(
            [pow(self.samples[i] - signal.samples[i], 2) for i in range(len(self.samples))]))

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
                    signal_f=dictionary['parameters']['signal_f'],
                    t1=dictionary['parameters']['t1'],
                    d=dictionary['parameters']['d'],
                    kw=dictionary['parameters']['kw'],
                    ts=dictionary['parameters']['ts'],
                    sampling_f=dictionary['parameters']['sampling_f'],
                    p=dictionary['parameters']['p'],
                    n1=dictionary['parameters']['n1'],
                    ns=dictionary['parameters']['ns'],
                    M=dictionary['parameters']['M'],
                    cutoff_f=dictionary['parameters']['cutoff_f'],
                ),
                dictionary['samples'],
                dictionary['type'])
            return signal


def print_yellow(text):
    print('\033[93m' + text + '\033[0m')
