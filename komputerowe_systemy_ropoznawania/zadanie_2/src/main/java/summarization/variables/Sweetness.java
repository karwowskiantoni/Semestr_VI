package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.trapezium;

public class Sweetness {
    public enum Label {
        NOT_SWEET, SWEET, VERY_SWEET, MADE_OF_SUGAR
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case NOT_SWEET -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.sweetness(), 0, 0, 1, 2)
                    ).collect(toList());
            case SWEET -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.sweetness(), 0.5, 2, 6, 8)
                    ).collect(toList());
            case VERY_SWEET -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.sweetness(), 5, 9, 13, 15)
                    ).collect(toList());
            case MADE_OF_SUGAR -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.sweetness(), 13, 16, 20, 20)
                    ).collect(toList());
        };
    }
}
