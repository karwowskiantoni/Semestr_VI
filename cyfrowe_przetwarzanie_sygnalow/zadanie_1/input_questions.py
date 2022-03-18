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


command = {
    'type': 'list',
    'name': 'command',
    'message': 'Welcome to SignalGenerator',
    'choices': ["generate", "sum", "difference", "product", "divide", "exit"]
}
A = {
    'type': "input",
    "name": "A",
    "message": "Enter the signal amplitude (A)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
T = {
    'type': "input",
    "name": "T",
    "message": "Enter the signal interval (T)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
t1 = {
    'type': "input",
    "name": "t1",
    "message": "Enter the signal starting time (t1)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
d = {
    'type': "input",
    "name": "d",
    "message": "Enter the signal length (d)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
kw = {
    'type': "input",
    "name": "kw",
    "message": "Enter the fill factor (kw)",
    "validate": ZeroToOneValidator,
    "filter": lambda val: float(val)
}
ts = {
    'type': "input",
    "name": "ts",
    "message": "Enter the probe jump time (ts)",
    "validate": FloatValidator,
    "filter": lambda val: float(val)
}
f = {
    'type': "input",
    "name": "f",
    "message": "Enter the signal frequency (f)",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
}
p = {
    'type': "input",
    "name": "p",
    "message": "Enter the signal noise probability (p)",
    "validate": ZeroToOneValidator,
    "filter": lambda val: float(val)
}
n1 = {
    'type': "input",
    "name": "n1",
    "message": "Enter the signal first probe number (n1)",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
}
ns = {
    'type': "input",
    "name": "ns",
    "message": "Enter the signal chosen probe (ns)",
    "validate": IntegerValidator,
    "filter": lambda val: int(val)
}
