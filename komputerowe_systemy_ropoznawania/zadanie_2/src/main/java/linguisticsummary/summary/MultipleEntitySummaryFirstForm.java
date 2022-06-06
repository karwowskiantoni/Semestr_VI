package linguisticsummary.summary;

import linguisticsummary.model.*;

import static linguisticsummary.model.FuzzySets.sigmaCount;

public class MultipleEntitySummaryFirstForm implements Summary {

    private final Quantifier quantifier;
    private final Summarizer summarizer;
    private final Entity firstEntity;
    private final Entity secondEntity;

    public MultipleEntitySummaryFirstForm(Quantifier quantifier, Summarizer summarizer, Entity firstEntity, Entity secondEntity) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
    }

    public String toString() {
        return quantifier + " of" + firstEntity + " in comparison to " + secondEntity + " are " + summarizer;
    }

    public Row toRow() {
        return null;
    }

    public double degreeOfTruth() {
        return quantifier.membership(
                (sigmaCount(summarizer.getMealLabels(), firstEntity.getMeals()) / firstEntity.size()) /
                        (
                                (sigmaCount(summarizer.getMealLabels(), firstEntity.getMeals()) / firstEntity.size())
                                        + (sigmaCount(summarizer.getMealLabels(), secondEntity.getMeals()) / secondEntity.size())
                        )
        );
    }
}
