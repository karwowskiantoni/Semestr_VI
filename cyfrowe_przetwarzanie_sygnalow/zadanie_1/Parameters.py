class Parameters:
    def __init__(self, A=None, T=None, t1=None, d=None, kw=None, ts=None, f=None, p=None, n1=None, ns=None):
        self.A = A
        self.T = T
        self.t1 = t1
        self.d = d
        self.kw = kw
        self.ts = ts
        self.f = f
        self.p = p
        self.n1 = n1
        self.ns = ns

    def print(self):
        print(self.A)
