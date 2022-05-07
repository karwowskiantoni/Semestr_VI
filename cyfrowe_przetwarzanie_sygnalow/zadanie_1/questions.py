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
    'choices': ["generate", "quantize", "sum", "difference", "product", "divide", "plot", "hist", "stat", "exit"]
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
                "noise impulse"]
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


divisions = {
    'type': 'list',
    'name': 'divisions',
    'message': 'select histogram number of divisions',
    'choices': ["5", "10", "15", "20", "25"],
    'filter': lambda val: int(val)
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
T = {
    'type': "input",
    "name": "T",
    "default": "2",
    "message": "Enter the signal interval (T)",
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
f = {
    'type': "input",
    "name": "f",
    "default": "60",
    "message": "Enter the signal frequency (f)",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
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
