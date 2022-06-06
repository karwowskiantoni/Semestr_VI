package linguisticsummary.summary;

import linguisticsummary.model.*;
import linguisticsummary.row.MultipleEntityRow;
import linguisticsummary.row.Row;
import linguisticsummary.row.SingleEntityRowSecondForm;

import static linguisticsummary.model.FuzzySets.*;

public class MultipleEntitySummaryFourthForm implements Summary {

    private final Summarizer summarizer;
    private final Entity firstEntity;
    private final Entity secondEntity;

    public MultipleEntitySummaryFourthForm(Summarizer summarizer, Entity firstEntity, Entity secondEntity) {
        this.summarizer = summarizer;
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
    }

    public String toString() {
        return "more " + firstEntity + " than " + secondEntity + " are " + summarizer;
    }

    public Row toRow() {
        return new MultipleEntityRow(toString(), formatResult(degreeOfTruth()));
    }

    private double degreeOfTruth() {
        return 1 - Math.min(1,
                (1 -
                        sigmaCount(summarizer.getMealLabels(), firstEntity.getMeals()) / firstEntity.size() +
                        sigmaCount(summarizer.getMealLabels(), secondEntity.getMeals()) / secondEntity.size()
                )
        );
    }
}