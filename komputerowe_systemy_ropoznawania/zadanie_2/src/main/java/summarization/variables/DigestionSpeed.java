package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.gauss;

public class DigestionSpeed {
    public enum Label {
        SLUGGISH, SLOW, STANDARD, FAST, RAPID
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case SLUGGISH -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.digestionSpeed(), 0, 0.1)
                    ).collect(toList());
            case SLOW -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.digestionSpeed(), 0.3, 0.1)
                    ).collect(toList());
            case STANDARD -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.digestionSpeed(), 0.5, 0.1)
                    ).collect(toList());
            case FAST -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.digestionSpeed(), 0.7, 0.1)
                    ).collect(toList());
            case RAPID -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.digestionSpeed(), 1, 0.1)
                    ).collect(toList());
        };
    }
}
