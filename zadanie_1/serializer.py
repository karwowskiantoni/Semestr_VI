import json

from Signal import Signal


def serialize(filename, signal):
    with open(filename, "w") as file:
        file.write(signal.toJSON())


def deserialize(filename):
    return json.loads(filename, object_hook=lambda d: Signal(**d))
