import cli
from Signal import Signal
from Parameters import Parameters
from functions import gauss_noise, uniform_noise, sinus, half_rectified_sinus, rectified_sinus, unit_jump, rectangular, \
    symmetrical_rectangular, triangular, unit_impulse, noise_impulse

if __name__ == '__main__':
    while True:
        cli.execute()

    signal = Signal.generate(gauss_noise, Parameters(t1=0, f=60, d=10, A=10))
    signal.print_plot()
    signal.print_histogram(10)

    signal = Signal.generate(sinus, Parameters(t1=0, f=1000, d=10, A=10, T=2))
    signal.print_plot()
    signal.print_histogram(50)

    signal = Signal.generate(half_rectified_sinus, Parameters(t1=0, f=60, d=10, A=10, T=2))
    signal.print_plot()

    signal = Signal.generate(rectified_sinus, Parameters(t1=0, f=60, d=10, A=10, T=2))
    signal.print_plot()

    signal = Signal.generate(rectangular, Parameters(t1=0, f=60, d=10, A=10, kw=0.5, T=2))
    signal.print_plot()

    signal = Signal.generate(symmetrical_rectangular, Parameters(t1=0, f=60, d=10, A=10, kw=0.5, T=3))
    signal.print_plot()

    signal = Signal.generate(triangular, Parameters(t1=0, f=60, d=10, A=10, kw=0.5, T=2))
    signal.print_plot()

    signal = Signal.generate(unit_jump, Parameters(t1=0, f=60, d=10, A=10, ts=5))
    signal.print_plot()

    signal = Signal.generate(unit_impulse, Parameters(t1=0, f=10, d=10, ns=3, A=1))
    signal.print_plot(linear=False)

    signal = Signal.generate(noise_impulse, Parameters(t1=0, f=10, d=10, A=10, p=0.1))
    signal.print_plot(linear=False)

    signal.serialize("test")
