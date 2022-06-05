package linguisticsummary.summary;

import linguisticsummary.model.*;

import java.util.stream.Stream;

import static java.lang.Math.*;
import static linguisticsummary.model.FuzzySets.*;

public class SingleEntitySummarySecondForm implements Summary {
    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final Entity entity;

    public SingleEntitySummarySecondForm(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer, Entity entity) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.entity = entity;
    }

    public String toString() {
        return quantifier + " of " + entity + " having " + qualifier + " are " + summarizer;
    }

    public double degreeOfTruth() {
        return quantifier.membership(sigmaCount(summarizer.getMealLabels(), entity.getMeals()) / sigmaCount(qualifier.getLabels(), entity.getMeals()));
    }

    public double degreeOfImprecision() {
        Double multipliedDegreesOfFuzziness = summarizer
                .getMealLabels()
                .stream()
                .map(variable ->
                        degreeOfFuzziness(variable, entity.getMeals())
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
                .map(variable -> sigmaCount(variable, entity.getMeals()) / entity.size())
                .reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(summarizerCardinalityProduct, 1 / (summarizer.getMealLabels().size() * 1.0)));
    }

    public double lengthOfQualifier() {
        return 2 * pow(0.5, qualifier.getLabels().size());
    }

    public double degreeOfCovering() {
      return support(
              Stream.concat(summarizer.getMealLabels().stream(), qualifier.getLabels().stream()).toList(),
              entity.getMeals()
      ).stream().mapToDouble(Double::doubleValue).sum() / entity.size();
    }

    public double degreeOfAppropriateness() {
        double supportProduct = summarizer
                .getMealLabels()
                .stream()
                .map(variable -> degreeOfFuzziness(variable, entity.getMeals()))
                .mapToDouble(Double::doubleValue)
                .reduce(1.0, (a, b) -> a * b);
        return abs(supportProduct - degreeOfCovering());
    }

    public double degreeOfQualifierImprecision() {
        return 1 - round(pow(degreeOfFuzziness(qualifier.getLabels(), entity.getMeals()), 1 / (qualifier.getLabels().size() * 1.0)));
    }

    public double degreeOfQualifierCardinality() {
            double qualifierCardinalityProduct = qualifier
                    .getLabels()
                    .stream()
                    .map(variable ->
                            sigmaCount(variable, entity.getMeals()) / entity.size()
                    ).reduce(1.0, (a, b) -> a * b);
            return 1 - round(pow(qualifierCardinalityProduct, 1 / (qualifier.getLabels().size() * 1.0)));
    }
}
