package linguisticsummary.variables;

import linguisticsummary.Meal;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Quantifier {
    private final String label;
    private final Function<List<Meal>, Double> membership;

    public Quantifier(String label, Function<List<Meal>, Double> membership) {
        this.label = label;
        this.membership = membership;
    }

    public String getLabel() {
        return label;
    }

    public Function<List<Meal>, Double> getMembership() {
        return membership;
    }
}
