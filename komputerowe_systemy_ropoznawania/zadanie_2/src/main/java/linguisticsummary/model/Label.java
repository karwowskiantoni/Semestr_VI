package linguisticsummary.model;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math.optimization.fitting.GaussianFunction;

import java.io.Serializable;
import java.util.List;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public abstract class Label implements Serializable {
    public enum FunctionType {
        TRAPEZIUM, GAUSS
    }
    private final String label;
    private final FunctionType type;
    private final List<Double> params;
    private final List<Double> domain;

    public Label(String label, FunctionType type, List<Double> params, List<Double> domain) {
        this.label = label;
        this.type = type;
        this.params = params;
        this.domain = domain;
    }

    public double membership(double value) {
        return switch (type) {
            case TRAPEZIUM -> trapezium(value, params.get(0), params.get(1), params.get(2), params.get(3));
            case GAUSS -> gauss(value, params.get(0), params.get(1));
        };
    }

    public double calculateIntegral() {
        try{
            return switch (type) {
                case TRAPEZIUM -> ((params.get(3) - params.get(0))
                        + (params.get(2) - params.get(1))) / 2;
                case GAUSS -> new TrapezoidIntegrator().integrate(
                        new GaussianFunction(0, 1, params.get(0),
                                params.get(1)), domain.get(0), domain.get(1));
            };
        } catch (MaxIterationsExceededException | FunctionEvaluationException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getLabel() {
        return label;
    }

    public List<Double> getDomain() {
        return domain;
    }

    private static double trapezium(
            double x,
            double begin,
            double firstFold,
            double secondFold,
            double end) {
        if (x < begin) {
            return 0;
        } else if (begin <= x && x < firstFold) {
            return (x - begin) / (firstFold - begin);
        } else if (firstFold <= x && x < secondFold) {
            return 1;
        } else if (secondFold <= x && x < end) {
            return (end - x) / (end - secondFold);
        } else return 0;
    }

    private static double gauss(double x, double centerPosition, double width) {
        return exp(-((pow(x - centerPosition, 2)) / (2 * pow(width, 2))));
    }
}
