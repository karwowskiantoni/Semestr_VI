from PyInquirer import prompt

from Parameters import Parameters
from Signal import Signal
from functions import uniform_noise, sinus, half_rectified_sinus, \
    rectified_sinus, rectangular, symmetrical_rectangular, triangular, \
    unit_jump, unit_impulse, noise_impulse, gauss_noise, impulse_response
from questions import signal_type, t1, d, A, \
    kw, p, ns, ts, existing_signal, divisions, name_with_default, level, quantize_type, interpolation_type, \
    command_type, new_f, M, sampling_f, signal_f, cutoff_f


def ask(question):
    return list(prompt([question]).values())[0]


def multiple_ask(questions):
    return prompt(questions)


if __name__ == '__main__':
    while True:
        command = ask(command_type)
        if command == "sample":
            function = ask(signal_type)

            if function == "uniform noise":
                parameters = Parameters(**multiple_ask([t1, sampling_f, d, A]))
                signal = Signal.generate(uniform_noise, parameters)
            elif function == "gauss noise":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d])))
                signal = Signal.generate(gauss_noise, parameters)
            elif function == "sinus":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, signal_f])))
                signal = Signal.generate(sinus, parameters)
            elif function == "half rectified sinus":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, signal_f])))
                signal = Signal.generate(half_rectified_sinus, parameters)
            elif function == "rectified sinus":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, signal_f])))
                signal = Signal.generate(rectified_sinus, parameters)
            elif function == "rectangular":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, signal_f, kw])))
                signal = Signal.generate(rectangular, parameters)
            elif function == "symmetrical rectangular":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, signal_f, kw])))
                signal = Signal.generate(symmetrical_rectangular, parameters)
            elif function == "triangular":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, signal_f, kw])))
                signal = Signal.generate(triangular, parameters)
            elif function == "unit jump":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, ts])))
                signal = Signal.generate(unit_jump, parameters)
            elif function == "unit impulse":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, ns])))
                signal = Signal.generate(unit_impulse, parameters)
            elif function == "impulse response":
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, M, cutoff_f])))
                signal = Signal.generate(impulse_response, parameters)
            else:
                parameters = Parameters(**(multiple_ask([t1, sampling_f, d, A, p])))
                signal = Signal.generate(noise_impulse, parameters)
            signal.serialize(ask(name_with_default(signal.type)))

        elif command == "quantize":
            signal = Signal.deserialize(ask(existing_signal()))
            if ask(quantize_type) == "flat":
                quantized = signal.quantize_flat(ask(level))
            else:
                quantized = signal.quantize_round(ask(level))
            quantized.serialize(ask(name_with_default(quantized.type)))

        elif command == "convolve":
            signal = Signal.deserialize(ask(existing_signal()))
            signal_2 = Signal.deserialize(ask(existing_signal()))
            convolved = signal.convolve(signal_2)
            convolved.serialize(ask(name_with_default(convolved.type)))

        elif command == "correlate":
            signal = Signal.deserialize(ask(existing_signal()))
            signal_2 = Signal.deserialize(ask(existing_signal()))
            correlated = signal.correlate(signal_2)
            correlated.serialize(ask(name_with_default(correlated.type)))

        elif command == "filter low pass rectangular":
            signal = Signal.deserialize(ask(existing_signal()))
            filtered = signal.filter_low_pass_rectangular(**(multiple_ask([M, cutoff_f])))
            filtered.serialize(ask(name_with_default(filtered.type)))

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
            signal = Signal.deserialize(ask(existing_signal()))
            signal.print_plot()
            # signal.print_histogram(ask(divisions))
            signal.print_stats()

        elif command == "sum":
            signal = Signal.deserialize(ask(existing_signal())).sum(Signal.deserialize(ask(existing_signal())))
            signal.serialize(ask(name_with_default(signal.type)))
        elif command == "difference":
            signal = Signal.deserialize(ask(existing_signal())).difference(Signal.deserialize(ask(existing_signal())))
            signal.serialize(ask(name_with_default(signal.type)))
        elif command == "product":
            signal = Signal.deserialize(ask(existing_signal())).product(Signal.deserialize(ask(existing_signal())))
            signal.serialize(ask(name_with_default(signal.type)))
        elif command == "divide":
            signal = Signal.deserialize(ask(existing_signal())).divide(Signal.deserialize(ask(existing_signal())))
            signal.serialize(ask(name_with_default(signal.type)))

        elif command == "exit":
            exit(0)
