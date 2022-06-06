package linguisticsummary.summary;

import linguisticsummary.model.Entity;
import linguisticsummary.model.Summarizer;
import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;

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
        return null;
    }

    public double degreeOfTruth(Quantifier quantifier, Summarizer summarizer, List<Meal> entity) {
        if (quantifier.isAbsolute()) {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), entity));
        } else {
            return quantifier.membership(sigmaCount(summarizer.getMealLabels(), entity) / entity.size());
        }
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

    public double degreeOfCovering() {
        return support(summarizer.getMealLabels(), entity.getMeals()).stream().mapToDouble(Double::doubleValue).sum() / entity.size();
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
}
