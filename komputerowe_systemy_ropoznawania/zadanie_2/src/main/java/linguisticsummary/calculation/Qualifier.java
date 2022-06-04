package linguisticsummary.calculation;

import linguisticsummary.model.Label;
import linguisticsummary.model.Meal;
import linguisticsummary.model.MealLabel;

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

    public String linguinize() {
        if (mealLabels.size() == 0) {
            return "";
        } else {
            String sentence = " having " + mealLabels.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
            return sentence.substring(0, sentence.length() - 5);
        }
    }
}