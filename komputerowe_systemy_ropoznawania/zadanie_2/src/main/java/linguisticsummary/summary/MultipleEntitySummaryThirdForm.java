package linguisticsummary.summary;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Qualifier;
import linguisticsummary.model.Quantifier;
import linguisticsummary.model.Summarizer;

import java.util.List;

public class MultipleEntitySummaryThirdForm implements Summary {

    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final List<Meal> firstEntity;
    private final List<Meal> secondEntity;

    public MultipleEntitySummaryThirdForm(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer, List<Meal> firstEntity, List<Meal> secondEntity) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
    }

    public String toString() {
        return quantifier + " of meals from first group having " + qualifier + " in comparison to second group are " + summarizer;
    }

    public double degreeOfTruth() {
        return 0.0;
    }
}
