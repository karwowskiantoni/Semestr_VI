from Parameters import Parameters
from functions import impulse_response

import matplotlib.pyplot as plt

plt.plot([i for i in range(100)], [impulse_response(Parameters(sampling_f=100, M=100, cutoff_f=30), i) for i in range (100)])
plt.show()
