package linguisticsummary.summary;

import linguisticsummary.model.*;
import linguisticsummary.row.Row;
import linguisticsummary.row.SingleEntityRowSecondForm;

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

    public Row toRow() {
        double truth = degreeOfTruth();
        double imprecision = degreeOfImprecision();
        double covering = degreeOfCovering();
        double appropriateness = degreeOfAppropriateness();
        double lengthOfSummary = lengthOfSummary();
        return new SingleEntityRowSecondForm(
                toString(),
                formatResult(truth),
                formatResult(imprecision),
                formatResult(covering),
                formatResult(appropriateness),
                formatResult(lengthOfSummary),
                formatResult(degreeOfQuantifierImprecision()),
                formatResult(degreeOfQuantifierCardinality()),
                formatResult(degreeOfSummarizerCardinality()),
                formatResult(degreeOfQualifierImprecision()),
                formatResult(degreeOfQualifierCardinality()),
                formatResult(lengthOfQualifier()),
                formatResult((0.5 * truth) + (0.2 * imprecision) + (0.1 * covering) + (0.1 * appropriateness) + (0.1 * lengthOfSummary))
        );
    }

    private double degreeOfTruth() {
        return quantifier.membership(sigmaCount(Stream.concat(summarizer.getMealLabels().stream(), qualifier.getLabels().stream()).toList(), entity.getMeals()) / sigmaCount(qualifier.getLabels(), entity.getMeals()));
    }

    private double degreeOfImprecision() {
        Double multipliedDegreesOfFuzziness = summarizer
                .getMealLabels()
                .stream()
                .map(variable ->
                        degreeOfFuzziness(variable, entity.getMeals())
                ).reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(multipliedDegreesOfFuzziness, 1.0 / (summarizer.getMealLabels().size() * 1.0)));
    }

    private double lengthOfSummary() {
        return 2 * pow(0.5, summarizer.getMealLabels().size());
    }

    private double degreeOfQuantifierImprecision() {
        return 1 - ((quantifier.getDomain().get(1) - quantifier.getDomain().get(0)) / (quantifier.isAbsolute() ? entity.size() : 1));
    }

    private double degreeOfQuantifierCardinality() {
        return quantifier.isAbsolute() ? quantifier.calculateIntegral() / entity.size() : quantifier.calculateIntegral();
    }

    private double degreeOfSummarizerCardinality() {
        double summarizerCardinalityProduct = summarizer
                .getMealLabels()
                .stream()
                .map(variable -> sigmaCount(variable, entity.getMeals()) / entity.size())
                .reduce(1.0, (a, b) -> a * b);
        return 1 - round(pow(summarizerCardinalityProduct, 1 / (summarizer.getMealLabels().size() * 1.0)));
    }

    private double lengthOfQualifier() {
        return 2 * pow(0.5, qualifier.getLabels().size());
    }

    private double degreeOfCovering() {
      return support(
              Stream.concat(summarizer.getMealLabels().stream(), qualifier.getLabels().stream()).toList(),
              entity.getMeals()
      ).stream().mapToDouble(Double::doubleValue).sum() / entity.size();
    }

    private double degreeOfAppropriateness() {
        double supportProduct = summarizer
                .getMealLabels()
                .stream()
                .map(variable -> degreeOfFuzziness(variable, entity.getMeals()))
                .mapToDouble(Double::doubleValue)
                .reduce(1.0, (a, b) -> a * b);
        return abs(supportProduct - degreeOfCovering());
    }

    private double degreeOfQualifierImprecision() {
        return 1 - round(pow(degreeOfFuzziness(qualifier.getLabels(), entity.getMeals()), 1 / (qualifier.getLabels().size() * 1.0)));
    }

    private double degreeOfQualifierCardinality() {
            double qualifierCardinalityProduct = qualifier
                    .getLabels()
                    .stream()
                    .map(variable ->
                            sigmaCount(variable, entity.getMeals()) / entity.size()
                    ).reduce(1.0, (a, b) -> a * b);
            return 1 - round(pow(qualifierCardinalityProduct, 1 / (qualifier.getLabels().size() * 1.0)));
    }
}
