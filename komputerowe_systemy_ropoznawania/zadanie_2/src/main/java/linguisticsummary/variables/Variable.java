package linguisticsummary.variables;

import linguisticsummary.Meal;

import java.util.function.Function;

public class Variable {
    private final String label;
    private final Function<Meal, Double> membership;

    public Variable(String label, Function<Meal, Double> membership) {
        this.label = label;
        this.membership = membership;
    }

    public String getLabel() {
        return label;
    }

    public Function<Meal, Double> getMembership() {
        return membership;
    }
}
