package linguisticsummary.calculation;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;
import linguisticsummary.model.Variable;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math.optimization.fitting.GaussianFunction;

import java.util.List;
import java.util.stream.Stream;

import static linguisticsummary.calculation.FuzzySets.*;
import static linguisticsummary.calculation.FuzzySets.sigmaCount;

import static java.lang.Math.*;

public class Summary {
    private final List<Meal> meals;

    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;

    private final double degreeOfTruth;
    private final double degreeOfImprecision;
    private final double degreeOfCovering;
    private final double degreeOfAppropriateness;
    private final double lengthOfSummary;
    private final double degreeOfQuantifierImprecision;
    private final double degreeOfQuantifierCardinality;
    private final double degreeOfSummarizerCardinality;
    private final double degreeOfQualifierImprecision;
    private final double degreeOfQualifierCardinality;
    private final double lengthOfQualifier;
    private final double optimalSummary;

    public Summary(List<Meal> meals, Quantifier quantifier, Qualifier qualifier, Summarizer summarizer) {
        this.meals = meals;

        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;

        this.degreeOfTruth = degreeOfTruth();
        this.degreeOfImprecision = degreeOfImprecision();
        this.degreeOfCovering = degreeOfCovering();
        this.degreeOfAppropriateness = degreeOfAppropriateness();
        this.lengthOfSummary = lengthOfSummary();
        this.degreeOfQuantifierImprecision = degreeOfQuantifierImprecision();
        this.degreeOfQuantifierCardinality = degreeOfQuantifierCardinality();
        this.degreeOfSummarizerCardinality = degreeOfSummarizerCardinality();
        this.degreeOfQualifierImprecision = degreeOfQualifierImprecision();
        this.degreeOfQualifierCardinality = degreeOfQualifierCardinality();
        this.lengthOfQualifier = lengthOfQualifier();
        this.optimalSummary = optimalSummary();
    }

    public String linguinize() {
        return quantifier.getLabel() + " of meals " + qualifier.linguinize() + summarizer.linguinize();
    }

    public String measures() {
        return "truth: " + degreeOfTruth + System.lineSeparator() +
                "imprecision " + degreeOfImprecision + System.lineSeparator() +
                "covering: " + degreeOfCovering + System.lineSeparator() +
                "appropriateness: " + degreeOfAppropriateness + System.lineSeparator() +
                "length of summary: " + lengthOfSummary + System.lineSeparator() +
                "quantifier imprecision: " + degreeOfQuantifierImprecision + System.lineSeparator() +
                "quantifier cardinality: " + degreeOfQuantifierCardinality + System.lineSeparator() +
                "summarizer cardinality: " + degreeOfSummarizerCardinality + System.lineSeparator() +
                "qualifier imprecision: " + degreeOfQualifierImprecision + System.lineSeparator() +
                "qualifier cardinality: " + degreeOfQualifierCardinality + System.lineSeparator() +
                "length of quantifier: " + lengthOfQualifier + System.lineSeparator() +
                "optimal summary: " + optimalSummary + System.lineSeparator();
    }

    private double degreeOfTruth() {
        if (qualifier.getVariables().size() == 0 && quantifier.isAbsolute()) {
            return quantifier.getMembership().apply(sigmaCount(summarizer.getVariables(), meals));
        } else if (qualifier.getVariables().size() == 0) {
            return quantifier.getMembership().apply(sigmaCount(summarizer.getVariables(), meals) / meals.size());
        } else {
            return quantifier.getMembership().apply(sigmaCount(summarizer.getVariables(), meals) / sigmaCount(qualifier.getVariables(), meals));
        }
    }

    private double degreeOfImprecision() {
        List<Variable> variables = summarizer.getVariables();
        Double multipliedDegreesOfFuzziness = variables
                .stream()
                .map(variable ->
                        degreeOfFuzziness(variable, meals)
                ).reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(multipliedDegreesOfFuzziness, 1.0 / (variables.size() * 1.0)));
    }

    private double degreeOfCovering() {
        List<Variable> allVariables = Stream.concat(summarizer.getVariables().stream(), qualifier.getVariables().stream()).toList();
        return support(allVariables, meals).stream().mapToDouble(Double::doubleValue).sum() / meals.size();
    }

    private double degreeOfAppropriateness() {
        List<Double> degreesOfFuzziness = summarizer
                .getVariables()
                .stream()
                .map(variable ->
                        degreeOfFuzziness(variable, meals)
                ).toList();
        double supportProduct = degreesOfFuzziness.stream().mapToDouble(Double::doubleValue).reduce(1.0, (a, b) -> a * b);
        return abs(supportProduct - degreeOfCovering());
    }

    private double lengthOfSummary() {
        return 2 * pow(0.5, summarizer.getVariables().size());
    }

    private double degreeOfQuantifierImprecision() {
        int cardinality;
        if (quantifier.isAbsolute()) {
            cardinality = meals.size();
        } else {
            cardinality = 1;
        }
        return 1 - ((quantifier.getDomain().getValue() - quantifier.getDomain().getKey()) / cardinality);
    }

    private double calculateTrapeziumIntegral(){
        Double begin = quantifier.getFunctionParams().get(0);
        Double firstFold = quantifier.getFunctionParams().get(1);
        Double secondFold = quantifier.getFunctionParams().get(2);
        Double end = quantifier.getFunctionParams().get(3);
        return  ((end - begin) + (secondFold - firstFold)) / 2;
    }

    private double calculateGaussIntegral(){
        TrapezoidIntegrator integrator = new TrapezoidIntegrator();

        double centerPosition = quantifier.getFunctionParams().get(0);
        double width = quantifier.getFunctionParams().get(1);
        try {
            return integrator.integrate(
                    new GaussianFunction(0, 1, centerPosition, width),
                    quantifier.getDomain().getKey(),
                    quantifier.getDomain().getValue());
        } catch (MaxIterationsExceededException | FunctionEvaluationException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private double degreeOfQuantifierCardinality() {
        double integralValue;

        if (quantifier.isAbsolute()) {
            if (quantifier.isGauss()) {
                integralValue = calculateGaussIntegral();
            } else {
                integralValue = calculateTrapeziumIntegral();
            }
            return integralValue / meals.size();
        } else {
            if (quantifier.isGauss()) {
                integralValue = calculateGaussIntegral();
            } else {
                integralValue = calculateTrapeziumIntegral();
            }
            return integralValue;
        }
    }

    private double degreeOfSummarizerCardinality() {
        double summarizerCardinalityProduct = summarizer
                .getVariables()
                .stream()
                .map(variable ->
                        sigmaCount(variable, meals) / meals.size()
                ).reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(summarizerCardinalityProduct, 1 / (summarizer.getVariables().size() * 1.0)));
    }

    private double degreeOfQualifierImprecision() {
        if (qualifier.getVariables().size() == 0) {
            return 0;
        } else {
            return 1 - round(pow(degreeOfFuzziness(qualifier.getVariables(), meals), 1 / (qualifier.getVariables().size() * 1.0)));
        }
    }

    private double degreeOfQualifierCardinality() {
        if (qualifier.getVariables().size() == 0) {
            return 0;
        } else {
            double qualifierCardinalityProduct = qualifier
                    .getVariables()
                    .stream()
                    .map(variable ->
                            sigmaCount(variable, meals) / meals.size()
                    ).reduce(1.0, (a, b) -> a * b);
            return 1 - round(pow(qualifierCardinalityProduct, 1 / (qualifier.getVariables().size() * 1.0)));
        }
    }

    private double lengthOfQualifier() {
        return 2 * pow(0.5, qualifier.getVariables().size());
    }

    public double optimalSummary() {
        return 0.4 * degreeOfTruth +
                0.3 * degreeOfImprecision +
                0.1 * degreeOfCovering +
                0.1 * degreeOfAppropriateness +
                0.1 * lengthOfSummary;
    }

}
