from PyInquirer import prompt

from Parameters import Parameters
from functions import uniform_noise, sinus, half_rectified_sinus, rectified_sinus, rectangular, symmetrical_rectangular, \
    triangular, unit_jump, unit_impulse, noise_impulse, gauss_noise
from input_questions import command_type, signal_type, t1, f, d, A, name, T, kw, p, ns, ts, generate_available_signals, \
    divisions, get_name
from Signal import Signal


def main():
    command = prompt([command_type])["command_type"]
    if command == "generate":
        function = prompt([signal_type])["signal_type"]

        if function == "uniform noise":
            signal = Signal.generate(uniform_noise, Parameters(**(prompt([t1, f, d, A]))))
        elif function == "gauss noise":
            signal = Signal.generate(gauss_noise, Parameters(**(prompt([t1, f, d]))))
        elif function == "sinus":
            signal = Signal.generate(sinus, Parameters(**(prompt([t1, f, d, A, T]))))
        elif function == "half rectified sinus":
            signal = Signal.generate(half_rectified_sinus, Parameters(**(prompt([t1, f, d, A, T]))))
        elif function == "rectified sinus":
            signal = Signal.generate(rectified_sinus, Parameters(**(prompt([t1, f, d, A, T]))))
        elif function == "rectangular":
            signal = Signal.generate(rectangular, Parameters(**(prompt([t1, f, d, A, T, kw]))))
        elif function == "symmetrical rectangular":
            signal = Signal.generate(symmetrical_rectangular, Parameters(**(prompt([t1, f, d, A, T, kw]))))
        elif function == "triangular":
            signal = Signal.generate(triangular, Parameters(**(prompt([t1, f, d, A, T, kw]))))
        elif function == "unit jump":
            signal = Signal.generate(unit_jump, Parameters(**(prompt([t1, f, d, A, ts]))))
        elif function == "unit impulse":
            signal = Signal.generate(unit_impulse, Parameters(**(prompt([t1, f, d, A, ns]))))
        elif function == "noise impulse":
            signal = Signal.generate(noise_impulse, Parameters(**(prompt([t1, f, d, A, p]))))
        signal.print_plot().serialize(prompt([get_name(signal.type)])["name"])

    elif command == "plot":
        Signal.deserialize(prompt([generate_available_signals()])["available_signals"]).print_plot()
    elif command == "hist":
        Signal.deserialize(prompt([generate_available_signals()])["available_signals"]).print_histogram(prompt([divisions])["divisions"])
    elif command == "stat":
        Signal.deserialize(prompt([generate_available_signals()])["available_signals"]).print_stats()
    elif command == "sum":
        Signal.deserialize(prompt([generate_available_signals()])["available_signals"])\
            .sum(Signal.deserialize(prompt([generate_available_signals()])["available_signals"]))\
            .print_plot()\
            .serialize(prompt([name])["name"])
    elif command == "difference":
        Signal.deserialize(prompt([generate_available_signals()])["available_signals"]) \
            .difference(Signal.deserialize(prompt([generate_available_signals()])["available_signals"])) \
            .print_plot() \
            .serialize(prompt([name])["name"])
    elif command == "product":
        Signal.deserialize(prompt([generate_available_signals()])["available_signals"]) \
            .product(Signal.deserialize(prompt([generate_available_signals()])["available_signals"])) \
            .print_plot() \
            .serialize(prompt([name])["name"])
    elif command == "divide":
        signal = Signal.deserialize(prompt([generate_available_signals()])["available_signals"]) \
            .divide(Signal.deserialize(prompt([generate_available_signals()])["available_signals"])) \
            .print_plot()
        signal.serialize(prompt([get_name(signal.type)])["name"])

    elif command == "exit":
        exit(0)
    main()
