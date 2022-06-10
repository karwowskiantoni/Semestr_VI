class Parameters:
    def __init__(self, A=None, signal_f=None, t1=None, d=None, kw=None, ts=None, sampling_f=None, p=None, n1=None,
                 ns=None, cutoff_f=None,
                 M=None):
        self.A = A
        self.signal_f = signal_f
        self.t1 = t1
        self.d = d
        self.kw = kw
        self.ts = ts
        self.sampling_f = sampling_f
        self.cutoff_f = cutoff_f
        self.p = p
        self.n1 = n1
        self.ns = ns
        self.M = M

    def copy(self):
        return Parameters(self.A, self.signal_f, self.t1, self.d, self.kw, self.ts, self.sampling_f, self.p, self.n1,
                          self.ns, self.cutoff_f, self.M)

    def print(self):
        print(self.A)
