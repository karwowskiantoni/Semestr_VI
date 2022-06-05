package linguisticsummary.summary;

import linguisticsummary.model.Qualifier;
import linguisticsummary.model.Summarizer;
import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.*;
import static linguisticsummary.model.FuzzySets.*;

public class SingleEntitySummarySecondForm implements Summary {
    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final List<Meal> entity;

    public SingleEntitySummarySecondForm(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer, List<Meal> entity) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.entity = entity;
    }

    public String toString() {
        return quantifier + " of entity having " + qualifier + " are " + summarizer;
    }

    public double degreeOfTruth() {
        if (quantifier.isAbsolute()) {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), entity) / sigmaCount(qualifier.getVariables(), entity)) / entity.size();
        } else {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), entity) / sigmaCount(qualifier.getVariables(), entity));
        }
    }

    public double degreeOfImprecision() {
        Double multipliedDegreesOfFuzziness = summarizer
                .getMealLabels()
                .stream()
                .map(variable ->
                        degreeOfFuzziness(variable, entity)
                ).reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(multipliedDegreesOfFuzziness, 1.0 / (summarizer.getMealLabels().size() * 1.0)));
    }

    public double lengthOfSummary() {
        return 2 * pow(0.5, summarizer.getMealLabels().size());
    }

    public double degreeOfQuantifierImprecision() {
        return 1 - ((quantifier.getDomain().get(1) - quantifier.getDomain().get(0)) / (quantifier.isAbsolute() ? entity.size() : 1));
    }

    public double degreeOfQuantifierCardinality() {
        return quantifier.isAbsolute() ? quantifier.calculateIntegral() / entity.size() : quantifier.calculateIntegral();
    }

    public double degreeOfSummarizerCardinality() {
        double summarizerCardinalityProduct = summarizer
                .getMealLabels()
                .stream()
                .map(variable -> sigmaCount(variable, entity) / entity.size())
                .reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(summarizerCardinalityProduct, 1 / (summarizer.getMealLabels().size() * 1.0)));
    }

    public double lengthOfQualifier() {
        return 2 * pow(0.5, qualifier.getVariables().size());
    }

    public double degreeOfCovering() {
      return support(
              Stream.concat(summarizer.getMealLabels().stream(), qualifier.getVariables().stream()).toList(),
              entity
      ).stream().mapToDouble(Double::doubleValue).sum() / entity.size();
    }

    public double degreeOfAppropriateness() {
        double supportProduct = summarizer
                .getMealLabels()
                .stream()
                .map(variable -> degreeOfFuzziness(variable, entity))
                .mapToDouble(Double::doubleValue)
                .reduce(1.0, (a, b) -> a * b);
        return abs(supportProduct - degreeOfCovering());
    }

    public double degreeOfQualifierImprecision() {
        return 1 - round(pow(degreeOfFuzziness(qualifier.getVariables(), entity), 1 / (qualifier.getVariables().size() * 1.0)));
    }

    public double degreeOfQualifierCardinality() {
            double qualifierCardinalityProduct = qualifier
                    .getVariables()
                    .stream()
                    .map(variable ->
                            sigmaCount(variable, entity) / entity.size()
                    ).reduce(1.0, (a, b) -> a * b);
            return 1 - round(pow(qualifierCardinalityProduct, 1 / (qualifier.getVariables().size() * 1.0)));
    }
}
