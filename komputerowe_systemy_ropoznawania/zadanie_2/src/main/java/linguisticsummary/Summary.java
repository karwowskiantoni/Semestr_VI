package linguisticsummary;

import linguisticsummary.variables.Quantifier;

public class Summary {
    private final Quantifier quantifier;
    private final Summarizer summarizer;

    public Summary(Quantifier quantificator, Summarizer summarizer) {
        this.quantifier = quantificator;
        this.summarizer = summarizer;
    }

    public String linguinize() {
        return quantifier.getLabel() + " are " + summarizer.linguinize();
    }
}
