package linguisticsummary.model;

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

    public String toString() {
        String sentence = mealLabels.stream().map(label -> label + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }
}
