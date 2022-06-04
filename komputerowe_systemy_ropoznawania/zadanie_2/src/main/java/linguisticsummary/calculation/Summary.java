package linguisticsummary.calculation;

import linguisticsummary.model.LabelFunction;
import linguisticsummary.model.Meal;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.MaxIterationsExceededException;
import org.apache.commons.math.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math.optimization.fitting.GaussianFunction;

import java.util.List;

public class Summary {

    private final LabelFunction<Double> quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;
    private final boolean isAbsolute;
    private Measures measures;


    public Summary(LabelFunction<Double> quantifier, Qualifier qualifier, Summarizer summarizer, Boolean isAbsolute) {
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
