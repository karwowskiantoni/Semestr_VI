package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.trapezium;

public class Antidepressiveness {
    public enum Label {
        NO_IMPACT_ON_DEPRESSION, LITTLE_IMPACT_ON_DEPRESSION, IMPACTFUL_ON_DEPRESSION, HIGHLY_ANTIDEPRESSANT
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case NO_IMPACT_ON_DEPRESSION -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.antidepressiveness(), 0, 0, 30, 50)
                    ).collect(toList());
            case LITTLE_IMPACT_ON_DEPRESSION -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.antidepressiveness(), 40, 60, 80, 100)
                    ).collect(toList());
            case IMPACTFUL_ON_DEPRESSION -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.antidepressiveness(), 80, 100, 110, 120)
                    ).collect(toList());
            case HIGHLY_ANTIDEPRESSANT -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.antidepressiveness(), 100, 120, 250, 250)
                    ).collect(toList());
        };
    }
}
