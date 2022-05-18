import pandas as pd
from tqdm import tqdm

from swarm.swarm import gl_pso
from functions import \
    rosenbrock, rosenbrock_domain, \
    sphere, sphere_domain, \
    step, step_domain, \
    quartic, quartic_domain, \
    exponential, exponential_domain, \
    f_two, f_two_domain, \
    schwefel, schwefel_domain


def gl_pso_default(function, domain, dimensions, iterations, obl=False, de=False):
    return gl_pso(
        function=function,
        domain=domain,
        dimensions_number=dimensions,
        iteration_number=iterations,
        population_size=20,
        inertia_weight=0.2,
        mutation_probability=0.01,
        stopping_gap=10000,
        obl=obl,
        de_mutation=de
    )


if __name__ == '__main__':
    results = pd.DataFrame()
    pd.set_option('display.max_rows', 500)
    pd.set_option('display.max_columns', 500)
    pd.set_option('display.width', 300)
    for _ in tqdm(range(3), ncols=100, colour="#36c25b"):
        for dimension in [30, 50, 100]:
            for iterations in [20, 50, 100]:
                for function in [(rosenbrock, rosenbrock_domain),
                                 (sphere, sphere_domain),
                                 (step, step_domain),
                                 (quartic, quartic_domain),
                                 (exponential, exponential_domain),
                                 # (f_two, f_two_domain),
                                 (schwefel, schwefel_domain)]:
                    results = results.append({"function": function[0].__name__,
                                              "dimensions": dimension,
                                              "iterations": iterations,
                                              "gl_pso": gl_pso_default(function[0], function[1], dimension, iterations),
                                              "gl_pso_obl": gl_pso_default(function[0], function[1], dimension, iterations, obl=True),
                                              "gl_pso_de": gl_pso_default(function[0], function[1], dimension, iterations, de=True)},
                                             ignore_index=True)

    table = results.groupby(["function", "dimensions", "iterations"]).agg(["min", "max"])
    table2 = results.groupby(["function", "dimensions", "iterations"]).agg(["mean", "std"])
    print(table.to_latex(longtable=True, float_format="%.2f", multicolumn=True))
    print(table2.to_latex(longtable=True, float_format="%.2f", multicolumn=True))
