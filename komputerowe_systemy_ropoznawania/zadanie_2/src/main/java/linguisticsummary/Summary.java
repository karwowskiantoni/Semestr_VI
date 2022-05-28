package linguisticsummary;

import linguisticsummary.variables.Quantifier;

public class Summary {
    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;

    public Summary(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
    }

    public String linguinize() {
        return quantifier.getLabel() + qualifier.linguinize() +  " of meals are " + summarizer.linguinize();
    }
}
