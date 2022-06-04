package linguisticsummary.calculation;

import linguisticsummary.model.Label;
import linguisticsummary.model.Meal;

import java.util.List;
import java.util.stream.Collectors;

public class Qualifier {
    private final List<Label<Meal>> labels;

    public Qualifier(List<Label<Meal>> labels) {
        this.labels = labels;
    }

    public List<Label<Meal>> getVariables() {
        return labels;
    }

    public String linguinize() {
        if (labels.size() == 0) {
            return "";
        } else {
            String sentence = " having " + labels.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
            return sentence.substring(0, sentence.length() - 5);
        }
    }
}