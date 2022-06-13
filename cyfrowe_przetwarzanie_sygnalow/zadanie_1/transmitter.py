import matplotlib.pyplot as plt
import matplotlib.animation as animation
from PyInquirer import prompt

from Parameters import Parameters
from Signal import Signal
from functions import sinus, rectangular
from questions_2 import object_v, signal_v, sampling_f, T, d, scan_interval


def ask(question):
    return list(prompt([question]).values())[0]


# time unit is constant = 1 ms
object_velocity = ask(object_v)  # m/s
signal_velocity = ask(signal_v)  # m/s

T = ask(T)  # s
signal_f = 1 / T  # [1]
sampling_f = ask(sampling_f)  # [1]
d = ask(d)  # s

interval = ask(scan_interval)  # s


def transmission_signal(d, sampling_f, signal_f):
    sinus_1 = Signal.generate(sinus, Parameters(t1=0, d=d, sampling_f=sampling_f, A=2, signal_f=signal_f * 2))
    sinus_2 = Signal.generate(sinus, Parameters(t1=0, d=d, sampling_f=sampling_f, A=1, signal_f=signal_f * 3))
    rectangular_1 = Signal.generate(rectangular,Parameters(t1=0, d=d, sampling_f=sampling_f, A=5, signal_f=signal_f, kw=0.5))
    return sinus_1.sum(sinus_2).difference(rectangular_1)


def base_position(time_in_seconds):
    return time_in_seconds * object_velocity


def real_delta_t(position):
    return (position / signal_velocity) * 2


def calculate_position(time_in_seconds):
    return (signal_velocity * time_in_seconds) / 2


transmitter = transmission_signal(d, sampling_f, signal_f)
receiver = transmission_signal(d, sampling_f, signal_f)
correlation = transmitter.correlate(receiver)

fig, axs = plt.subplots(3)
axs[2].axis((0, 100, 0, 5))
line_1, = axs[0].plot(transmitter.X(), transmitter.samples)
line_2, = axs[0].plot(receiver.X(), receiver.samples)
line_3, = axs[1].plot(correlation.X(), correlation.samples)
line_4 = axs[2].bar(0, 1, width=5).patches[0]
lines = [line_1, line_2, line_3, line_4]


def animate(i):
    i = (i * interval)
    position = base_position(i)
    delta_t = real_delta_t(position)

    transmitter_rotated = transmitter.rotate_seconds(i)
    receiver_rotated = receiver.rotate_seconds(i - delta_t)
    correlation_rotated = receiver_rotated.correlate(transmitter_rotated)

    calculated_delta_t = correlation_rotated.delta_time_in_correlation()
    calculated_position = calculate_position(calculated_delta_t)
    phrase = f"delta_t: {int(delta_t*1000)}/{int(calculated_delta_t*1000)}[ms] (real/calculated)\n" \
             f"position: {round(base_position(i), 2)}/{round(calculated_position, 2)}[m] (real/calculated)"
    fig.suptitle(phrase)
    line_1.set_ydata(transmitter_rotated.samples)
    line_2.set_ydata(receiver_rotated.samples)
    line_3.set_ydata(correlation_rotated.samples)
    line_4.set(x=position)
    return lines,


animation_1 = animation.FuncAnimation(fig, animate, interval=(interval*1000))
plt.show()
