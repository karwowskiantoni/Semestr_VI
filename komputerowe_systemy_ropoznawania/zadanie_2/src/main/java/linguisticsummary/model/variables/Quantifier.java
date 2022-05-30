package linguisticsummary.model.variables;

import java.util.function.Function;

public class Quantifier {
    private final String label;
    private final Function<Double, Double> membership;
    private final boolean isAbsolute;


    public Quantifier(String label, Function<Double, Double> membership, boolean isAbsolute) {
        this.label = label;
        this.membership = membership;
        this.isAbsolute = isAbsolute;
    }

    public String getLabel() {
        return label;
    }

    public Function<Double, Double> getMembership() {
        return membership;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }
}
