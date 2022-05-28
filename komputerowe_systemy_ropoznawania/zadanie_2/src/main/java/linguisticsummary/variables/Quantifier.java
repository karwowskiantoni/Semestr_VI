package linguisticsummary.variables;

import linguisticsummary.Meal;

import java.util.List;
import java.util.function.BiFunction;

public class Quantifier {
    private final String label;
    private final BiFunction<Meal, List<Meal>, Double> membership;

    public Quantifier(String label, BiFunction<Meal, List<Meal>, Double> membership) {
        this.label = label;
        this.membership = membership;
    }

    public String getLabel() {
        return label;
    }

    public BiFunction<Meal, List<Meal>, Double> getMembership() {
        return membership;
    }
}
