package linguisticsummary.calculation;

import linguisticsummary.model.LabelFunction;
import linguisticsummary.model.Meal;

import java.util.List;
import java.util.stream.Collectors;

public class Qualifier {
    private final List<LabelFunction<Meal>> labelFunctions;

    public Qualifier(List<LabelFunction<Meal>> labelFunctions) {
        this.labelFunctions = labelFunctions;
    }

    public List<LabelFunction<Meal>> getVariables() {
        return labelFunctions;
    }

    public String linguinize() {
        if (labelFunctions.size() == 0) {
            return "";
        } else {
            String sentence = " having " + labelFunctions.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
            return sentence.substring(0, sentence.length() - 5);
        }
    }
}