package linguisticsummary.summary;

import linguisticsummary.model.Entity;
import linguisticsummary.model.Summarizer;
import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;
import linguisticsummary.row.Row;
import linguisticsummary.row.SingleEntityRowFirstForm;
import linguisticsummary.row.SingleEntityRowSecondForm;

import java.util.List;

import static java.lang.Math.*;
import static linguisticsummary.model.FuzzySets.*;

public class SingleEntitySummaryFirstForm implements Summary {

    private final Quantifier quantifier;
    private final Summarizer summarizer;
    private final Entity entity;

    public SingleEntitySummaryFirstForm(Quantifier quantifier, Summarizer summarizer, Entity entity) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.entity = entity;
    }

    public String toString() {
        return quantifier + " of " + entity + " are " + summarizer;
    }

    public Row toRow() {
        double truth = degreeOfTruth();
        double imprecision = degreeOfImprecision();
        double covering = degreeOfCovering();
        double appropriateness = degreeOfAppropriateness();
        double lengthOfSummary = lengthOfSummary();
        return new SingleEntityRowFirstForm(
                toString(),
                formatResult(truth),
                formatResult(imprecision),
                formatResult(covering),
                formatResult(appropriateness),
                formatResult(lengthOfSummary),
                formatResult(degreeOfQuantifierImprecision()),
                formatResult(degreeOfQuantifierCardinality()),
                formatResult(degreeOfSummarizerCardinality()),
                formatResult((0.5 * truth) + (0.2 * imprecision) + (0.1 * covering) + (0.1 * appropriateness) + (0.1 * lengthOfSummary))
        );
    }

    private double degreeOfTruth() {
        if (quantifier.isAbsolute()) {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), entity.getMeals()));
        } else {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), entity.getMeals()) / entity.size());
        }
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

    private double degreeOfCovering() {
        return support(summarizer.getMealLabels(), entity.getMeals()).stream().mapToDouble(Double::doubleValue).sum() / entity.size();
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
}
