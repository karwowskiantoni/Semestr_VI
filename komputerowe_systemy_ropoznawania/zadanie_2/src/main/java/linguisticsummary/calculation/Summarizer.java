package linguisticsummary.calculation;

import linguisticsummary.model.Label;
import linguisticsummary.model.Meal;

import java.util.List;
import java.util.stream.Collectors;

public class Summarizer {
    private final List<Label<Meal>> labels;

    public Summarizer(List<Label<Meal>> labels) {
        this.labels = labels;
    }

    public List<Label<Meal>> getLabelFunctions() {
        return labels;
    }

    public String linguinize() {
        String sentence = "are " + labels.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }

}
