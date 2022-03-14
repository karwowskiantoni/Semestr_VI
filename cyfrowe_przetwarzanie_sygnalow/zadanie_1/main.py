from Signal import Signal
from zadanie_1.Parameters import Parameters
from zadanie_1.functions import gauss_noise, uniform_noise, sinus

if __name__ == '__main__':
    signal = Signal(sinus, Parameters(t1=0, f=60, d=10, A=10, T=100))
    signal.serialize("test")
    signal.print_plot()
    signal = Signal(gauss_noise, Parameters(t1=0, f=60, d=10, A=10))
    signal.print_plot()
    signal = Signal.deserialize("test")
    signal.print_plot()


