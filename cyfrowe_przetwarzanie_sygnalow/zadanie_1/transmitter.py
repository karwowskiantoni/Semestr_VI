import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation

from Signal import Signal

signal_1 = Signal.deserialize("transmitter")
signal_2 = Signal.deserialize("transmitter")
signal_3 = signal_1.correlate(signal_2)

fig, ax = plt.subplots()
# line_1, = ax.plot(signal_1.X(), signal_1.samples)
# line_2, = ax.plot(signal_2.X(), signal_2.samples)
# lines = [line_1, line_2]

correlation, = ax.plot(signal_3.X(), signal_3.samples)


def animate(i):
    # signal_1.rotate_times(5)
    # signal_2.rotate_times(6)
    # line_1.set_ydata(signal_1.samples)
    # line_2.set_ydata(signal_2.samples)

    correlation.set_ydata(signal_3.samples)
    return correlation,


animation_1 = animation.FuncAnimation(fig, animate, interval=30)

plt.show()
