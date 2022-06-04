package linguisticsummary.calculation;

import linguisticsummary.model.Label;
import linguisticsummary.model.Meal;

import java.util.List;

public class Summary {

    private final Label<Double> quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final boolean isAbsolute;
    private Measures measures;


    public Summary(Label<Double> quantifier, Qualifier qualifier, Summarizer summarizer, Boolean isAbsolute) {
        this.isAbsolute = isAbsolute;

        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
    }


    public String linguinize(List<Meal> meals) {
        measures = new Measures(meals, quantifier, qualifier, summarizer, isAbsolute);
        return quantifier.getLabel() + " of meals " + qualifier.linguinize() + summarizer.linguinize();
    }

}
