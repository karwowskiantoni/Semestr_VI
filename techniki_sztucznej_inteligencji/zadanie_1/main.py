import matplotlib.pyplot as plt
from tqdm import tqdm

from functions import rosenbrock, ROSENBROCK_DOMAIN
from evolution.evolution import differential_evolution_algorithm

if __name__ == '__main__':
    function = rosenbrock
    domain = ROSENBROCK_DOMAIN

    i_function = lambda i: 0.001 * (i + 15)
    steps = 300

    x_values = [i_function(i) for i in range(steps)]
    results = []

    for i in tqdm(range(steps), ncols=100):
        result = differential_evolution_algorithm(function=function,
                                                  domain=domain,
                                                  dimensions_number=10,
                                                  population_size=20,
                                                  iteration_number=100,
                                                  amplification_factor=0.2,
                                                  crossing_factor=i_function(i))
        results.append(result)

    plt.plot(x_values, results, "#99b8ff")
    # plt.legend(["1", "2", "3"])
    plt.xlabel("crossing factor")
    plt.ylabel("best result")
    plt.show()
