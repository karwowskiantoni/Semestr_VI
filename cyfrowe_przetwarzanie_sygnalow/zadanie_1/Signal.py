import json
import matplotlib.pyplot as plt

from Parameters import Parameters


def generate_ticks(function, parameters):
    try:
        return [function(parameters, x / parameters.f) for x in range((parameters.d - parameters.t1) * parameters.f)]
    except Exception:
        print("empty parameter in function " + function.__name__)
        return [0]


class Signal:
    def __init__(self, parameters, ticks, type):
        self.parameters = parameters
        self.ticks = ticks
        self.type = type

    @staticmethod
    def generate(function, parameters):
        return Signal(parameters, generate_ticks(function, parameters), function.__name__)

    def print_plot(self, linear=True):
        plt.figure().suptitle(self.type)
        if linear:
            plt.plot([i / self.parameters.f for i in range(len(self.ticks))], self.ticks)
        else:
            plt.scatter([i / self.parameters.f for i in range(len(self.ticks))], self.ticks, s=1)
        plt.show()

    def print_histogram(self, divisions_number):
        plt.hist(self.ticks, bins=divisions_number)
        plt.show()

    def print_stats(self):
        print("------stats-------")
        print("chuj: " + str(len(self.ticks)))

    def serialize(self, filename):
        with open(filename, "w") as file:
            file.write(json.dumps(self, default=lambda o: o.__dict__, sort_keys=True, indent=4))

    @staticmethod
    def deserialize(filename):
        with open(filename, "r") as file:
            dictionary = json.loads(file.read())
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
