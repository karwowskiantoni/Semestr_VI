package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.trapezium;

public class Absorption {
    public enum Label {
        LITTLE_DIGESTED, MEDIOCRE_DIGESTED, FULLY_DIGESTED,
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case LITTLE_DIGESTED -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.absorption(), 80, 80, 83, 85)
                    ).collect(toList());
            case MEDIOCRE_DIGESTED -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.absorption(), 80, 85, 94, 97)
                    ).collect(toList());
            case FULLY_DIGESTED -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.absorption(), 95, 97, 99.8, 99.8)
                    ).collect(toList());
        };
    }
}
