package linguisticsummary.calculation;

import linguisticsummary.model.MealLabel;

import java.util.List;
import java.util.stream.Collectors;

public class Summarizer {
    private final List<MealLabel> mealLabels;

    public Summarizer(List<MealLabel> mealLabels) {
        this.mealLabels = mealLabels;
    }

    public List<MealLabel> getMealLabels() {
        return mealLabels;
    }

    public String linguinize() {
        String sentence = " are " + mealLabels.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }
}
