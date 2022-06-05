package linguisticsummary.summary;

import linguisticsummary.model.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static linguisticsummary.model.FuzzySets.sigmaCount;

public class MultipleEntitySummarySecondForm implements Summary {

    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final Entity firstEntity;
    private final Entity secondEntity;

    public MultipleEntitySummarySecondForm(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer, Entity firstEntity, Entity secondEntity) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
    }

    public String toString() {
        return quantifier + " of " + firstEntity + " in comparison to " + secondEntity + " having " + qualifier + " are " + summarizer;
    }

    public double degreeOfTruth() {
        return quantifier.membership(
                (sigmaCount(summarizer.getMealLabels(), firstEntity.getMeals()) / firstEntity.size()) /
                        (
                                (sigmaCount(summarizer.getMealLabels(), firstEntity.getMeals()) / firstEntity.size())
                                        + (sigmaCount(
                                        Stream.concat(summarizer.getMealLabels().stream(), qualifier.getLabels().stream()).toList(),
                                        secondEntity.getMeals()
                                ) / secondEntity.size())
                        )
        );
    }
}
