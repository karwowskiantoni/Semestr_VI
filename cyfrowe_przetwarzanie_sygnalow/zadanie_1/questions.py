from os import listdir

from prompt_toolkit.validation import Validator, ValidationError


class IntegerValidator(Validator):

    def validate(self, document):
        try:
            int(document.text)
        except ValueError:
            raise ValidationError(message="Please enter an integer",
                                  cursor_position=len(document.text))


class FloatValidator(Validator):

    def validate(self, document):
        try:
            float(document.text)
        except ValueError:
            raise ValidationError(message="Please enter a float",
                                  cursor_position=len(document.text))


class ZeroToOneValidator(Validator):

    def validate(self, document):
        try:
            if float(document.text) < 0:
                raise ValueError
            elif float(document.text) > 1:
                raise ValueError
        except ValueError:
            raise ValidationError(message="Please enter a float between 0 and 1",
                                  cursor_position=len(document.text))


command_type = {
    'type': 'list',
    'name': 'command_type',
    'message': 'choose option:',
    'choices': ["sample",
                "quantize",
                "interpolate",
                "sum",
                "difference",
                "product",
                "divide",
                "convolve",
                "correlate",
                "filter low pass rectangular",
                "filter mid pass rectangular",
                "filter low pass blackman",
                "filter mid pass blackman",
                "plot",
                "compare",
                "exit"]
}

signal_type = {
    'type': 'list',
    'name': 'signal_type',
    'message': 'choose function to generate signal:',
    'choices': ["uniform noise",
                "gauss noise",
                "sinus",
                "half rectified sinus",
                "rectified sinus",
                "rectangular",
                "symmetrical rectangular",
                "triangular",
                "unit jump",
                "unit impulse",
                "noise impulse",
                "impulse response"]
}
existing_signal_unfilled = {
    'type': 'list',
    'name': 'available_signals',
    'message': 'choose one of available signals:',
    'choices': []
}


def existing_signal():
    question = existing_signal_unfilled
    question['choices'] = [file.replace(".signal", "") for file in listdir("signals") if file.__contains__(".signal")]
    return question


interpolation_type = {
    'type': 'list',
    'name': 'interpolation_type',
    'message': 'choose interpolation type:',
    'choices': ["zero order hold", "first order hold", "sinc function"]
}
quantize_type = {
    'type': 'list',
    'name': 'quantize_type',
    'message': 'choose quantization type:',
    'choices': ["flat", "round"]
}
divisions = {
    'type': 'list',
    'name': 'divisions',
    'message': 'select histogram number of divisions',
    'choices': ["5", "10", "15", "20", "25"],
    'filter': lambda val: int(val)
}
new_f = {
    'type': "input",
    "name": "new frequency",
    "message": "enter sampling frequency for new signal",
    "default": "60",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
}
level = {
    'type': "input",
    "name": "level",
    "message": "Enter the quantization level",
    "default": "5",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
}
A = {
    'type': "input",
    "name": "A",
    "message": "Enter the signal amplitude (A)",
    "default": "5",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
signal_f = {
    'type': "input",
    "name": "signal_f",
    "default": "0.5",
    "message": "Enter the signal frequency (signal_f)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
t1 = {
    'type': "input",
    "name": "t1",
    "default": "0",
    "message": "Enter the signal starting time (t1)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
d = {
    'type': "input",
    "name": "d",
    "default": "10",
    "message": "Enter the signal length (d)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
M = {
    'type': "input",
    "name": "M",
    "default": "40",
    "message": "Enter filter order (M)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
cutoff_f = {
    'type': "input",
    "name": "cutoff_f",
    "default": "30",
    "message": "enter cutoff frequency (cutoff_f)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
kw = {
    'type': "input",
    "name": "kw",
    "default": "0.5",
    "message": "Enter the fill factor (kw)",
    "validate": ZeroToOneValidator,
    "filter": lambda val: float(val)
}
ts = {
    'type': "input",
    "name": "ts",
    "default": "5",
    "message": "Enter the probe jump time (ts)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
sampling_f = {
    'type': "input",
    "name": "sampling_f",
    "default": "60",
    "message": "enter sampling frequency (sampling_f)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
p = {
    'type': "input",
    "name": "p",
    "default": "0.2",
    "message": "Enter the signal noise probability (p)",
    "validate": ZeroToOneValidator,
    "filter": lambda val: float(val)
}
n1 = {
    'type': "input",
    "name": "n1",
    "default": "0",
    "message": "Enter the signal first probe number (n1)",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
}
ns = {
    'type': "input",
    "name": "ns",
    "default": "100",
    "message": "Enter the signal chosen probe (ns)",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
}
name = {
    'type': "input",
    "name": "name",
    "default": "test",
    "message": "Enter the signal name",
    "filter": lambda val: val
}


def name_with_default(default):
    question = name
    question['default'] = default
    return question
