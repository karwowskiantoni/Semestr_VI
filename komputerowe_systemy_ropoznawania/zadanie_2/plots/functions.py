import matplotlib.pyplot as plt
import numpy as np


def plot(function, domain):
    plt.plot(domain, [function(x) for x in domain])


def domain(start, stop):
    return np.arange(start, stop, 0.01)


def gaussian_function(x, center_position, width):
    return np.exp(- (pow(x - center_position, 2) / (2 * pow(width, 2))))


def trapezium_function(x, begin, first_fold, second_fold, end):
    if x < begin:
        return 0
    elif begin <= x < first_fold:
        return (x - begin) / (first_fold - begin)
    elif first_fold <= x < second_fold:
        return 1
    elif second_fold <= x < end:
        return (end - x) / (end - second_fold)
    elif x >= end:
        return 0
