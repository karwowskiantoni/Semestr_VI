package linguisticsummary;

public class Summary {
    private final Variable quantificator;
    private final Summarizer summarizer;

    public Summary(Variable quantificator, Summarizer summarizer) {
        this.quantificator = quantificator;
        this.summarizer = summarizer;
    }

    public String linguinize() {
        return quantificator.getLabel() + " are " + summarizer.linguinize();
    }
}
