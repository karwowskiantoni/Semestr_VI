class Signal:
    def __init__(self, frequency, start, length, amplitude, function):
        self.frequency = frequency
        self.start = start
        self.ticks = function(amplitude, start, length)