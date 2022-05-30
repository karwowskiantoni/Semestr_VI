package linguisticsummary.model;

import java.io.Serializable;
import java.util.function.Function;

public class Quantifier implements Serializable {
    private final String label;
    private final SerializableFunction membership;
    private final boolean absolute;


    public Quantifier(String label, SerializableFunction membership, boolean absolute) {
        this.label = label;
        this.membership = membership;
        this.absolute = absolute;
    }

    public String getLabel() {
        return label;
    }

    public SerializableFunction getMembership() {
        return membership;
    }

    public boolean isAbsolute() {
        return absolute;
    }

    public interface SerializableFunction extends Function<Double, Double>, Serializable {}
}
