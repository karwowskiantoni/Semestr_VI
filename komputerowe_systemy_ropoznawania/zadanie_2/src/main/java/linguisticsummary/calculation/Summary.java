package linguisticsummary.calculation;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;
import linguisticsummary.model.Variable;

import java.util.List;
import java.util.stream.Collectors;
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
                "length of summary: " + lengthOfSummary(meals) + System.lineSeparator() +
                "quantifier imprecision: " + degreeOfQuantifierImprecision(meals) + System.lineSeparator() +
                "quantifier cardinality: " + degreeOfQuantifierCardinality(meals) + System.lineSeparator() +
                "summarizer cardinality: " + degreeOfSummarizerCardinality(meals) + System.lineSeparator() +
                "qualifier imprecision: " + degreeOfQualifierImprecision(meals) + System.lineSeparator() +
                "qualifier cardinality: " + degreeOfQualifierCardinality(meals) + System.lineSeparator() +
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
        List<Double> conorm = tConorm(allVariables, meals);
        return (double) support(conorm) / meals.size();
    }

    private double degreeOfAppropriateness(List<Meal> meals) {
        return 0;
    }

    private double lengthOfSummary(List<Meal> meals) {
        return 0;
    }

    private double degreeOfQuantifierImprecision(List<Meal> meals) {
        return 0;
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

    private double optimalSummary(List<Meal> meals) {
        return 0;
    }
}
