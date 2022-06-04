package linguisticsummary.calculation;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Quantifier;

import java.util.List;

public class Summary {

    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final Measures measures;

    public Summary(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer, List<Meal> meals) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.measures = new Measures(meals, quantifier, qualifier, summarizer);
    }

    public Measures measures() {
        return measures;
    }

    public String toString() {
        return quantifier.getLabel() + " of meals " + qualifier.linguinize() + summarizer.linguinize() + System.lineSeparator() + measures;
    }
}
