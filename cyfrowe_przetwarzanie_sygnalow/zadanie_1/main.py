from PyInquirer import prompt

from Parameters import Parameters
from Signal import Signal
from functions import uniform_noise, sinus, half_rectified_sinus, \
    rectified_sinus, rectangular, symmetrical_rectangular, triangular, \
    unit_jump, unit_impulse, noise_impulse, gauss_noise
from questions import signal_type, t1, f, d, A, \
    T, kw, p, ns, ts, existing_signal, divisions, name_with_default, level, quantize_type, interpolation_type, \
    command_2, new_f


def ask(question):
    return list(prompt([question]).values())[0]


def multiple_ask(questions):
    return prompt(questions)


if __name__ == '__main__':
    while True:
        command = ask(command_2)
        if command == "generate" or command == "sample":
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
            else:
                parameters = Parameters(**(multiple_ask([t1, f, d, A, p])))
                signal = Signal.generate(noise_impulse, parameters)
            signal.serialize(ask(name_with_default(signal.type)))

        elif command == "quantize":
            signal = Signal.deserialize(ask(existing_signal()))
            if ask(quantize_type) == "flat":
                quantized = signal.quantize_flat(ask(level))
            else:
                quantized = signal.quantize_round(ask(level))
            quantized.serialize(ask(name_with_default(quantized.type)))

        elif command == "compare":
            signal = Signal.deserialize(ask(existing_signal()))
            signal_2 = Signal.deserialize(ask(existing_signal()))
            signal.print_comparison(signal_2)
            signal.print_plot(signal_2)

        elif command == "interpolate":
            signal = Signal.deserialize(ask(existing_signal()))
            chosen_type = ask(interpolation_type)
            if chosen_type == "zero order hold":
                interpolated = signal.interpolate_zero(ask(new_f))
            elif chosen_type == "first order hold":
                interpolated = signal.interpolate_first(ask(new_f))
            else:
                interpolated = signal.interpolate_sin(ask(new_f))
            interpolated.serialize(ask(name_with_default(interpolated.type)))

        elif command == "plot":
            Signal.deserialize(ask(existing_signal())).print_plot()
        elif command == "hist":
            Signal.deserialize(ask(existing_signal())).print_histogram(ask(divisions))
        elif command == "stat":
            Signal.deserialize(ask(existing_signal())).print_stats()

        elif command == "sum":
            signal = Signal.deserialize(ask(existing_signal())).sum(Signal.deserialize(ask(existing_signal())))
            signal.print_plot()
            signal.serialize(ask(name_with_default(signal.type)))
        elif command == "difference":
            signal = Signal.deserialize(ask(existing_signal())).difference(Signal.deserialize(ask(existing_signal())))
            signal.print_plot()
            signal.serialize(ask(name_with_default(signal.type)))
        elif command == "product":
            signal = Signal.deserialize(ask(existing_signal())).product(Signal.deserialize(ask(existing_signal())))
            signal.print_plot()
            signal.serialize(ask(name_with_default(signal.type)))
        elif command == "divide":
            signal = Signal.deserialize(ask(existing_signal())).divide(Signal.deserialize(ask(existing_signal())))
            signal.print_plot()
            signal.serialize(ask(name_with_default(signal.type)))

        elif command == "exit":
            exit(0)
