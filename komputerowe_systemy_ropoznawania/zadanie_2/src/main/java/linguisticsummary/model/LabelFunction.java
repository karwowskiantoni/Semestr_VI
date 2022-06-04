package linguisticsummary.model;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math.optimization.fitting.GaussianFunction;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class LabelFunction<T> implements Serializable {
    public enum FunctionType {
        TRAPEZIUM, GAUSS
    }
    private final String label;
    private final Function<T, Double> membership;
    private final FunctionType type;
    private final List<Double> functionParameters;
    private final List<Double> domain;

    public LabelFunction(String label, Function<T, Double> membership, FunctionType type, List<Double> functionParameters, List<Double> domain) {
        this.label = label;
        this.membership = membership;
        this.type = type;
        this.functionParameters = functionParameters;
        this.domain = domain;
    }

    public double calculateIntegral() {
        try{
            return switch (type) {
                case TRAPEZIUM -> ((functionParameters.get(3) - functionParameters.get(0))
                        + (functionParameters.get(2) - functionParameters.get(1))) / 2;
                case GAUSS -> new TrapezoidIntegrator().integrate(
                        new GaussianFunction(0, 1, functionParameters.get(0),
                                functionParameters.get(1)), domain.get(0), domain.get(1));
            };
        } catch (MaxIterationsExceededException | FunctionEvaluationException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getLabel() {
        return label;
    }

    public Function<T, Double> getMembership() {
        return membership;
    }

    public FunctionType getType() {
        return type;
    }

    public List<Double> getDomain() {
        return domain;
    }
}
