package linguisticsummary.summary;

import linguisticsummary.model.*;

import java.util.stream.Stream;

import static linguisticsummary.model.FuzzySets.sigmaCount;

public class MultipleEntitySummaryThirdForm implements Summary {

    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final Entity firstEntity;
    private final Entity secondEntity;

    public MultipleEntitySummaryThirdForm(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer, Entity firstEntity, Entity secondEntity) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
    }

    public String toString() {
        return quantifier + " of " + firstEntity + " having " + qualifier + " in comparison to " + secondEntity + " are " + summarizer;
    }

    public Row toRow() {
        return null;
    }

    public double degreeOfTruth() {
        return quantifier.membership(
                (sigmaCount(
                        Stream.concat(summarizer.getMealLabels().stream(), qualifier.getLabels().stream()).toList(),
                        firstEntity.getMeals()
                ) / firstEntity.size()) /
                        (
                                (sigmaCount(
                                        Stream.concat(summarizer.getMealLabels().stream(), qualifier.getLabels().stream()).toList(),
                                        firstEntity.getMeals())
                                        / firstEntity.size())
                                        + (sigmaCount(summarizer.getMealLabels(), secondEntity.getMeals()) / secondEntity.size())
                        )
        );
    }
}
