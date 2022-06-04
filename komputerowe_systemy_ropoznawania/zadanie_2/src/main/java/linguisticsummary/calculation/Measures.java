package linguisticsummary.calculation;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.*;
import static java.lang.Math.pow;
import static linguisticsummary.calculation.FuzzySets.*;

public class Measures {
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

    public Measures(List<Meal> meals, Quantifier quantifier, Qualifier qualifier, Summarizer summarizer) {
        this.degreeOfTruth = degreeOfTruth(qualifier, quantifier, summarizer, meals);
        this.degreeOfImprecision = degreeOfImprecision(summarizer, meals);
        this.degreeOfCovering = degreeOfCovering(summarizer, qualifier, meals);
        this.degreeOfAppropriateness = degreeOfAppropriateness(summarizer, qualifier, meals);
        this.lengthOfSummary = lengthOfSummary(summarizer);
        this.degreeOfQuantifierImprecision = degreeOfQuantifierImprecision(quantifier, meals);
        this.degreeOfQuantifierCardinality = degreeOfQuantifierCardinality(quantifier, meals);
        this.degreeOfSummarizerCardinality = degreeOfSummarizerCardinality(summarizer, meals);
        this.degreeOfQualifierImprecision = degreeOfQualifierImprecision(qualifier, meals);
        this.degreeOfQualifierCardinality = degreeOfQualifierCardinality(qualifier, meals);
        this.lengthOfQualifier = lengthOfQualifier(qualifier);
        this.optimalSummary = optimalSummary();
    }

    public double getOptimalSummary() {
        return optimalSummary;
    }

    public String toString() {
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

    private double degreeOfTruth(Qualifier qualifier, Quantifier quantifier, Summarizer summarizer, List<Meal> meals) {
        if (qualifier.getVariables().size() == 0 && quantifier.isAbsolute()) {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), meals));
        } else if (qualifier.getVariables().size() == 0) {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), meals) / meals.size());
        } else {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), meals) / sigmaCount(qualifier.getVariables(), meals));
        }
    }

    private double degreeOfImprecision(Summarizer summarizer, List<Meal> meals) {
        Double multipliedDegreesOfFuzziness = summarizer
                .getMealLabels()
                .stream()
                .map(variable ->
                        degreeOfFuzziness(variable, meals)
                ).reduce(1.0, (a, b) -> a * b);
    return 1 - round(pow(multipliedDegreesOfFuzziness, 1.0 / (summarizer.getMealLabels().size() * 1.0)));
  }

    private double degreeOfCovering(Summarizer summarizer, Qualifier qualifier, List<Meal> meals) {
        return support(
                Stream.concat(
                        summarizer.getMealLabels().stream(),
                        qualifier.getVariables().stream()).toList(),
                        meals)
                .stream().mapToDouble(Double::doubleValue).sum() / meals.size();
    }

    private double degreeOfAppropriateness(Summarizer summarizer, Qualifier qualifier, List<Meal> meals) {
      double supportProduct = summarizer
              .getMealLabels()
              .stream()
              .map(variable -> degreeOfFuzziness(variable, meals))
              .mapToDouble(Double::doubleValue)
              .reduce(1.0, (a, b) -> a * b);
        return abs(supportProduct - degreeOfCovering(summarizer, qualifier, meals));
    }

    private double lengthOfSummary(Summarizer summarizer) {
        return 2 * pow(0.5, summarizer.getMealLabels().size());
    }

    private double degreeOfQuantifierImprecision(Quantifier quantifier, List<Meal> meals) {
        return 1 - ((quantifier.getDomain().get(0) - quantifier.getDomain().get(1)) / (quantifier.isAbsolute() ? meals.size() : 1));
    }

    private double degreeOfQuantifierCardinality(Quantifier quantifier, List<Meal> meals) {
     return quantifier.isAbsolute() ? quantifier.calculateIntegral() / meals.size() : quantifier.calculateIntegral();
    }

    private double degreeOfSummarizerCardinality(Summarizer summarizer, List<Meal> meals) {
        double summarizerCardinalityProduct = summarizer
                .getMealLabels()
                .stream()
                .map(variable -> sigmaCount(variable, meals) / meals.size())
                .reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(summarizerCardinalityProduct, 1 / (summarizer.getMealLabels().size() * 1.0)));
    }

    private double degreeOfQualifierImprecision(Qualifier qualifier, List<Meal> meals) {
        if (qualifier.getVariables().size() == 0) {
            return 0;
        } else {
            return 1 - round(pow(degreeOfFuzziness(qualifier.getVariables(), meals), 1 / (qualifier.getVariables().size() * 1.0)));
        }
    }

    private double degreeOfQualifierCardinality(Qualifier qualifier, List<Meal> meals) {
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

    private double lengthOfQualifier(Qualifier qualifier) {
        return 2 * pow(0.5, qualifier.getVariables().size());
    }

    private double optimalSummary() {
        return 0.4 * degreeOfTruth +
                0.3 * degreeOfImprecision +
                0.1 * degreeOfCovering +
                0.1 * degreeOfAppropriateness +
                0.1 * lengthOfSummary;
    }
}
