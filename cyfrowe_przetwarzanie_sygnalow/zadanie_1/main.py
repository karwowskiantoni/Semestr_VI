from PyInquirer import prompt

from Parameters import Parameters
from functions import uniform_noise, sinus, half_rectified_sinus, rectified_sinus, rectangular, symmetrical_rectangular, \
    triangular, unit_jump, unit_impulse, noise_impulse, gauss_noise
from questions import command_type, signal_type, t1, f, d, A, name, T, kw, \
    p, ns, ts, existing_signal, \
    divisions, name_with_default, level
from Signal import Signal


def ask(question):
    return list(prompt([question]).values())[0]


def multiple_ask(questions):
    return prompt(questions)


def main():
    command = ask(command_type)
    if command == "generate":
        function = ask(signal_type)

        if function == "uniform noise":
            parameters = Parameters(**multiple_ask([t1, f, d, A]))
            signal = Signal.generate(uniform_noise, parameters)
        elif function == "gauss noise":
            parameters = Parameters(**(multiple_ask([t1, f, d])))
            signal = Signal.generate(gauss_noise, parameters)
        elif function == "sinus":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, T])))
            signal = Signal.generate(sinus, parameters)
        elif function == "half rectified sinus":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, T])))
            signal = Signal.generate(half_rectified_sinus, parameters)
        elif function == "rectified sinus":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, T])))
            signal = Signal.generate(rectified_sinus, parameters)
        elif function == "rectangular":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, T, kw])))
            signal = Signal.generate(rectangular, parameters)
        elif function == "symmetrical rectangular":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, T, kw])))
            signal = Signal.generate(symmetrical_rectangular, parameters)
        elif function == "triangular":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, T, kw])))
            signal = Signal.generate(triangular, parameters)
        elif function == "unit jump":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, ts])))
            signal = Signal.generate(unit_jump, parameters)
        elif function == "unit impulse":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, ns])))
            signal = Signal.generate(unit_impulse, parameters)
        elif function == "noise impulse":
            parameters = Parameters(**(multiple_ask([t1, f, d, A, p])))
            signal = Signal.generate(noise_impulse, parameters)
        signal.print_plot().serialize(ask(name_with_default(signal.type)))

    elif command == "quantize":
        Signal.deserialize(ask(existing_signal()))\
            .quantize_flat(ask(level))\
            .print_plot()\
            .serialize(ask(name))
    elif command == "plot":
        Signal.deserialize(ask(existing_signal())).print_plot()
    elif command == "hist":
        Signal.deserialize(ask(existing_signal())).print_histogram(ask(divisions))
    elif command == "stat":
        Signal.deserialize(ask(existing_signal())).print_stats()
    elif command == "sum":
        Signal.deserialize(ask(existing_signal())) \
            .sum(Signal.deserialize(ask(existing_signal()))) \
            .print_plot() \
            .serialize(ask(name))
    elif command == "difference":
        Signal.deserialize(ask(existing_signal())) \
            .difference(Signal.deserialize(ask(existing_signal()))) \
            .print_plot() \
            .serialize(ask(name))
    elif command == "product":
        Signal.deserialize(ask(existing_signal())) \
            .product(Signal.deserialize(ask(existing_signal()))) \
            .print_plot() \
            .serialize(ask(name))
    elif command == "divide":
        signal = Signal.deserialize(ask(existing_signal())) \
            .divide(Signal.deserialize(ask(existing_signal()))) \
            .print_plot()
        signal.serialize(ask(name_with_default(signal.type)))

    elif command == "exit":
        exit(0)
    main()


if __name__ == '__main__':
    main()
