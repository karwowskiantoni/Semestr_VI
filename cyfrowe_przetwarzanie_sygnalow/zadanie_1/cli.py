def execute():
    print("->", end='')
    commands = input("->").split(" ")

    if commands[0] == "ls":
        ls()
    elif commands[0] == "help":
        help()
    elif commands[0] == "exit":
        print_colored("exiting... good bye :)", WARNING)
        exit(0)
    elif commands[0] == "generate":
        print(commands[0])
    elif commands[0] == "sum":
        print(commands[0])
    elif commands[0] == "subtract":
        print(commands[0])
    elif commands[0] == "multiply":
        print(commands[0])
    elif commands[0] == "divide":
        print(commands[0])
    elif commands[0] == "plot":
        print(commands[0])
    elif commands[0] == "hist":
        print(commands[0])
    elif commands[0] == "stat":
        print(commands[0])
    else:
        print_colored("wrong command, use help", FAIL)


def ls():
    print_colored("there are no saved signals", WARNING)


def generate():
    print_colored("generate", WARNING)


def chuj():
    print_colored("generate", WARNING)


def help():
    print("------------------------------------------------------------------------------")
    print_colored("management: ", WARNING)
    print("    ls" + HEADER + " - list all saved impulses" + ENDC)
    print("    help" + HEADER + " - shows this help" + ENDC)
    print("    exit" + HEADER + " - exit program" + ENDC)
    print_colored("generating: ", WARNING)
    print("    generate" + OKCYAN + " <function name> <function arguments> <file name>" + ENDC)
    print_colored("calculating: ", WARNING)
    print("    sum" + OKCYAN + " <first input name> <second input name> <output name>" + ENDC)
    print("    subtract" + OKCYAN + " <first input name> <second input name> <output name>" + ENDC)
    print("    multiply" + OKCYAN + " <first input name> <second input name> <output name>" + ENDC)
    print("    divide" + OKCYAN + " <first input name> <second input name> <output name>" + ENDC)
    print_colored("statistic: ", WARNING)
    print("    plot" + OKCYAN + " <file name>" + ENDC)
    print("    hist" + OKCYAN + " <file name> <number of divisions>" + ENDC)
    print("    stat" + OKCYAN + " <file name>" + ENDC)
    print("------------------------------------------------------------------------------")
    print(ENDC)


def print_colored(text, color):
    print(color + text + ENDC)


HEADER = '\033[95m'
OKBLUE = '\033[94m'
OKCYAN = '\033[96m'
OKGREEN = '\033[92m'
WARNING = '\033[93m'
FAIL = '\033[91m'
ENDC = '\033[0m'
BOLD = '\033[1m'
UNDERLINE = '\033[4m'

