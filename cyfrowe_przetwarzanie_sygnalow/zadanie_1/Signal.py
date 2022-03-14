import json
import matplotlib.pyplot as plt


def generate_ticks(function, parameters):
    return [function(parameters, x) for x in range((parameters.d - parameters.t1) * parameters.f)]


class Signal:
    def __init__(self, function, parameters):
        self.parameters = parameters
        self.ticks = generate_ticks(function, parameters)

    def serialize(self, filename):
        with open(filename, "w") as file:
            file.write(json.dumps(self, default=lambda o: o.__dict__, sort_keys=True, indent=4))

    def print_plot(self):
        plt.plot([i for i in range(len(self.ticks))], self.ticks)
        plt.show()

    def print_histogram(self):
        plt.plot(self.ticks, [i for i in range(len(self.ticks))])
        plt.show()

    @staticmethod
    def deserialize(filename):
        return json.loads(filename, object_hook=lambda d: Signal(**d))



