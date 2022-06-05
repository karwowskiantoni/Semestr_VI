package linguisticsummary.summary;

import linguisticsummary.model.*;

import java.util.List;

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

    public double degreeOfTruth() {
        return 0.0;
    }
}
