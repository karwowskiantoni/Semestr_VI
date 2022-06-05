package linguisticsummary.summary;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Qualifier;
import linguisticsummary.model.Quantifier;
import linguisticsummary.model.Summarizer;

import java.util.List;

public class MultipleEntitySummaryFourthForm implements Summary {

    private final Summarizer summarizer;
    private final List<Meal> firstEntity;
    private final List<Meal> secondEntity;

    public MultipleEntitySummaryFourthForm(Summarizer summarizer, List<Meal> firstEntity, List<Meal> secondEntity) {
        this.summarizer = summarizer;
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
    }

    public String toString() {
        return "more meals from first group than meals from second group are " + summarizer;
    }

    public double degreeOfTruth() {
        return 0.0;
    }
}
