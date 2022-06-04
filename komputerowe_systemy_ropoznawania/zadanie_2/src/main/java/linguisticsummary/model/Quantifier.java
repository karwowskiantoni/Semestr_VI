package linguisticsummary.model;

import java.util.List;

public class Quantifier extends Label {
    private final boolean isAbsolute;
    public Quantifier(String label, FunctionType type, List<Double> params, List<Double> domain, boolean isAbsolute) {
        super(label, type, params, domain);
        this.isAbsolute = isAbsolute;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }
}
