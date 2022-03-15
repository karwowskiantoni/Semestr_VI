from Signal import Signal
from Parameters import Parameters
from functions import gauss_noise, uniform_noise, sinus, half_rectified_sinus, rectified_sinus, unit_jump, rectangular, \
    symmetrical_rectangular, triangular, unit_impulse, noise_impulse

if __name__ == '__main__':
    # signal = Signal(uniform_noise, Parameters(t1=0, f=60, d=10, A=10))
    # signal.print_plot()
    #
    # signal = Signal(gauss_noise, Parameters(t1=0, f=60, d=10, A=10))
    # signal.print_plot()
    #
    # signal = Signal(sinus, Parameters(t1=0, f=60, d=10, A=10, T=2))
    # signal.print_plot()
    #
    # signal = Signal(half_rectified_sinus, Parameters(t1=0, f=60, d=10, A=10, T=2))
    # signal.print_plot()
    #
    # signal = Signal(rectified_sinus, Parameters(t1=0, f=60, d=10, A=10, T=2))
    # signal.print_plot()
    #
    signal = Signal(rectangular, Parameters(t1=0, f=60, d=10, A=10, kw=0.5, T=2))
    signal.print_plot()
    signal.print_histogram(5)

    signal = Signal(symmetrical_rectangular, Parameters(t1=0, f=60, d=10, A=10, kw=0.5, T=3))
    signal.print_plot()

    signal = Signal(triangular, Parameters(t1=0, f=60, d=10, A=10, kw=0.5, T=2))
    signal.print_plot()

    signal = Signal(unit_jump, Parameters(t1=0, f=60, d=10, A=10, ts=5))
    signal.print_plot()

    signal = Signal(unit_impulse, Parameters(t1=0, f=60, d=10, A=10))
    signal.print_plot()

    signal = Signal(noise_impulse, Parameters(t1=0, f=60, d=10, A=10))
    signal.print_plot()

    signal.serialize("test")
