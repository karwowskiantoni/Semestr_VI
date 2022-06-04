package linguisticsummary.model;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.function.Function;

public class Quantifier implements Serializable {
    private final Pair<Double, Double> domain;
    private final String label;
    private final SerializableFunction membership;
    private final boolean absolute;


    public Quantifier(Pair<Double, Double> domain, String label, SerializableFunction membership, boolean absolute) {
        this.domain = domain;
        this.label = label;
        this.membership = membership;
        this.absolute = absolute;
    }

    public Pair<Double, Double> getDomain() {
        return domain;
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

    public interface SerializableFunction extends Function<Double, Double>, Serializable {
    }
}
