package linguisticsummary.summary;

import linguisticsummary.model.*;

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

    public double degreeOfTruth() {
        return 0.0;
    }
}
