package linguisticsummary.model;

import java.util.List;
import java.util.stream.Collectors;

public class Qualifier {
    private final List<MealLabel> mealLabels;

    public Qualifier(List<MealLabel> mealLabels) {
        this.mealLabels = mealLabels;
    }

    public List<MealLabel> getVariables() {
        return mealLabels;
    }

    public String toString() {
        String sentence = mealLabels.stream().map(label -> label + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }
}