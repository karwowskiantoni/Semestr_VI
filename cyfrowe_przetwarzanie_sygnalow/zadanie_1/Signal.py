import json
import matplotlib.pyplot as plt


def generate_ticks(function, parameters):
    try:
        return [function(parameters, x/parameters.f) for x in range((parameters.d - parameters.t1) * parameters.f)]
    except Exception:
        print("empty parameter in function " + function.__name__)
        return [0]


class Signal:
    def __init__(self, function, parameters):
        self.parameters = parameters
        self.type = function.__name__
        self.ticks = generate_ticks(function, parameters)

    def print_plot(self, linear=True):
        plt.figure().suptitle(self.type)
        if linear:
            plt.plot([i/self.parameters.f for i in range(len(self.ticks))], self.ticks)
        else:
            plt.scatter([i/self.parameters.f for i in range(len(self.ticks))], self.ticks, s=1)
        plt.show()

    def print_histogram(self, divisions_number):
        plt.hist(self.ticks, bins=divisions_number)
        plt.show()

    def serialize(self, filename):
        with open(filename, "w") as file:
            file.write(json.dumps(self, default=lambda o: o.__dict__, sort_keys=True, indent=4))

    @staticmethod
    def deserialize(filename):
        return json.loads(filename, object_hook=lambda d: Signal(**d))



