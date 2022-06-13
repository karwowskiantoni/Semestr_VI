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


T = {
    'type': "input",
    "name": "T",
    "default": "10",
    "message": "Enter the signal T [s]",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
d = {
    'type': "input",
    "name": "d",
    "default": "30",
    "message": "Enter the signal length [s]",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
sampling_f = {
    'type': "input",
    "name": "sampling_f",
    "default": "300",
    "message": "enter sampling frequency [1/s]",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
scan_interval = {
    'type': "input",
    "name": "scan_interval",
    "default": "0.5",
    "message": "Enter scan interval (ms)",
    "validate": ZeroToOneValidator,
    "filter": lambda val: float(val)
}
signal_v = {
    'type': "input",
    "name": "signal_v",
    "default": "100",
    "message": "Enter the signal velocity [m/s]",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
object_v = {
    'type': "input",
    "name": "object_v",
    "default": "1",
    "message": "Enter the object velocity [m/s]",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
