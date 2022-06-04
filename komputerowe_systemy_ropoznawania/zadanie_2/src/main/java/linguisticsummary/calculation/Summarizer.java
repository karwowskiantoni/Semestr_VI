package linguisticsummary.calculation;

import linguisticsummary.model.LabelFunction;
import linguisticsummary.model.Meal;

import java.util.List;
import java.util.stream.Collectors;

public class Summarizer {
    private final List<LabelFunction<Meal>> labelFunctions;

    public Summarizer(List<LabelFunction<Meal>> labelFunctions) {
        this.labelFunctions = labelFunctions;
    }

    public List<LabelFunction<Meal>> getLabelFunctions() {
        return labelFunctions;
    }

    public String linguinize() {
        String sentence = "are " + labelFunctions.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }

}
