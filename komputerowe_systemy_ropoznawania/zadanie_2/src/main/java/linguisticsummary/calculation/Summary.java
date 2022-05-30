package linguisticsummary.calculation;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;
import linguisticsummary.model.Variable;

import java.util.List;
import java.util.stream.Stream;

import static linguisticsummary.calculation.FuzzySets.*;
import static linguisticsummary.calculation.FuzzySets.sigmaCount;

import static java.lang.Math.*;

public class Summary {
    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;

    public Summary(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
    }

    public String linguinize(List<Meal> meals) {
        return quantifier.getLabel() + " of meals" + qualifier.linguinize() + summarizer.linguinize();
    }

    public String measures(List<Meal> meals) {
        return "truth: " + degreeOfTruth(meals) + System.lineSeparator() +
                "imprecision " + degreeOfImprecision(meals) + System.lineSeparator() +
                "covering: " + degreeOfCovering(meals) + System.lineSeparator() +
                "appropriateness: " + degreeOfAppropriateness(meals) + System.lineSeparator() +
                "length of summary: " + lengthOfSummary() + System.lineSeparator() +
                "quantifier imprecision: " + degreeOfQuantifierImprecision(meals) + System.lineSeparator() +
                "quantifier cardinality: " + degreeOfQuantifierCardinality(meals) + System.lineSeparator() +
                "summarizer cardinality: " + degreeOfSummarizerCardinality(meals) + System.lineSeparator() +
                "qualifier imprecision: " + degreeOfQualifierImprecision(meals) + System.lineSeparator() +
                "qualifier cardinality: " + degreeOfQualifierCardinality(meals) + System.lineSeparator() +
                "length of quantifier: " + lengthOfQualifier() + System.lineSeparator() +
                "optimal summary: " + optimalSummary(meals) + System.lineSeparator();
    }

    private double degreeOfTruth(List<Meal> meals) {
        if (qualifier.getVariables().size() == 0 && quantifier.isAbsolute()) {
            return quantifier.getMembership().apply(sigmaCount(summarizer.getVariables(), meals));
        } else if (qualifier.getVariables().size() == 0) {
            return quantifier.getMembership().apply(sigmaCount(summarizer.getVariables(), meals) / meals.size());
        } else {
            return quantifier.getMembership().apply(sigmaCount(summarizer.getVariables(), meals) / sigmaCount(qualifier.getVariables(), meals));
        }
    }

    private double degreeOfImprecision(List<Meal> meals) {
        List<Variable> variables = summarizer.getVariables();
        Double multipliedDegreesOfFuzziness = variables.stream().map(variable -> degreeOfFuzziness(variable, meals)).reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(multipliedDegreesOfFuzziness, 1.0 / (variables.size() * 1.0)));
    }

    private double degreeOfCovering(List<Meal> meals) {
        List<Variable> allVariables = Stream.concat(summarizer.getVariables().stream(), qualifier.getVariables().stream()).toList();
        return support(allVariables, meals).stream().mapToDouble(Double::doubleValue).sum() / meals.size();
    }

    private double degreeOfAppropriateness(List<Meal> meals) {
        List<Double> degreesOfFuzziness = summarizer.getVariables().stream().map(variable -> degreeOfFuzziness(variable, meals)).toList();
        double multiplicatedSupports = degreesOfFuzziness.stream().mapToDouble(Double::doubleValue).reduce(1.0, (a, b) -> a * b);
        return abs(multiplicatedSupports - degreeOfCovering(meals));

    }

    private double lengthOfSummary() {
        return 2 * pow(0.5, summarizer.getVariables().size());
    }

    private double degreeOfQuantifierImprecision(List<Meal> meals) {
//        quantifier.
        return 1;
    }

    private double degreeOfQuantifierCardinality(List<Meal> meals) {
        return 0;
    }

    private double degreeOfSummarizerCardinality(List<Meal> meals) {
        return 0;
    }

    private double degreeOfQualifierImprecision(List<Meal> meals) {
        return 0;
    }

    private double degreeOfQualifierCardinality(List<Meal> meals) {
        return 0;
    }

    private double lengthOfQualifier() {
        return 2 * pow(0.5, qualifier.getVariables().size());
    }

    public double optimalSummary(List<Meal> meals) {
        return 0.4 * degreeOfTruth(meals) +
                0.3 * degreeOfImprecision(meals) +
                0.1 * degreeOfCovering(meals) +
                0.1 * degreeOfAppropriateness(meals) +
                0.1 * lengthOfSummary();
    }
}
