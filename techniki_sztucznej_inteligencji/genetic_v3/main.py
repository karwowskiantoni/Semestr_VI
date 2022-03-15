import matplotlib.pyplot as plt

from swarm.functions import rosenbrock, ROSENBROCK_DOMAIN, sphere, SPHERE_DOMAIN
from genetic_v3.algorithm import differential_evolution_algorithm

if __name__ == '__main__':
    function = rosenbrock
    domain = ROSENBROCK_DOMAIN
    result = differential_evolution_algorithm(function, domain, 5, 500, "gowno", [0.5, 0.5])
    print(result)

    # plt.plot(x_values, minimums, "#99b8ff")
    # plt.plot(x_values, averages, "#6996ff")
    # plt.plot(x_values, maximums, "#0a54ff")
    plt.legend(["1", "2", "3"])
    plt.xlabel("japierdole")
    plt.ylabel("kurwa")
    plt.show()
