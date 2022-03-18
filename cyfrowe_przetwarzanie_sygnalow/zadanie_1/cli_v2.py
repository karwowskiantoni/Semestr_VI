from PyInquirer import prompt
from examples import custom_style_2, custom_style_3
from prompt_toolkit.validation import Validator, ValidationError

from input_questions import command

HEADER = '\033[95m'
OKBLUE = '\033[94m'
OKCYAN = '\033[96m'
OKGREEN = '\033[92m'
WARNING = '\033[93m'
FAIL = '\033[91m'
ENDC = '\033[0m'
BOLD = '\033[1m'
UNDERLINE = '\033[4m'


def add(a, b):
    print(a + b)


def difference(a, b):
    print(a - b)


def product(a, b):
    print(a * b)


def divide(a, b):
    print(a / b)


def main():
    chosen_command = prompt([command])["command"]
    # a = answers.get("a")
    # b = answers.get("b")
    if chosen_command == "sum":
        options = prompt(number, number)
        a, b = options["a"], options["a"]
        add(a, b)
    elif chosen_command == "difference":
        options = prompt(number, number)
        a, b = options[0], options[1]
        difference(a, b)
    elif chosen_command == "product":
        options = prompt(number, number)
        a, b = options[0], options[1]
        product(a, b)
    elif chosen_command == "divide":
        options = prompt(number, number)
        a, b = options[0], options[1]
        divide(a, b)
    elif chosen_command == "exit":
        exit(0)
