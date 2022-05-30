package linguisticsummary.model;

import java.io.Serializable;
import java.util.function.Function;

public class Variable implements Serializable {
    private final String label;
    private final SerializableFunction membership;

    public Variable(String label, SerializableFunction membership) {
        this.label = label;
        this.membership = membership;
    }

    public String getLabel() {
        return label;
    }

    public Function<Meal, Double> getMembership() {
        return membership;
    }

    public interface SerializableFunction extends Function<Meal, Double>, Serializable {}
}
