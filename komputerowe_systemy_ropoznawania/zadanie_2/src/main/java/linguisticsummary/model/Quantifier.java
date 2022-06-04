package linguisticsummary.model;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class Quantifier implements Serializable {
    private final Pair<Double, Double> domain;
    private final List<Double> functionParams;
    private final String label;
    private final SerializableFunction membership;
    private final boolean isAbsolute;
    private final boolean isGauss;


    public Quantifier(Pair<Double, Double> domain, List<Double> functionParams, String label, SerializableFunction membership, boolean isAbsolute, boolean isGauss) {
        this.domain = domain;
        this.functionParams= functionParams;
        this.label = label;
        this.membership = membership;
        this.isAbsolute = isAbsolute;
        this.isGauss = isGauss;
    }

    public Pair<Double, Double> getDomain() {
        return domain;
    }

    public List<Double> getFunctionParams() {
        return functionParams;
    }

    public boolean isGauss() {
        return isGauss;
    }

    public String getLabel() {
        return label;
    }

    public SerializableFunction getMembership() {
        return membership;
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }

    public interface SerializableFunction extends Function<Double, Double>, Serializable {
    }
}
