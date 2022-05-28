package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.trapezium;

public class ProteinContent {
    public enum Label {
        LITTLE_PROTEIN, MEDIOCRE_PROTEIN, HIGH_PROTEIN
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case LITTLE_PROTEIN -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.proteinContent(), 0, 0, 5, 10)
                    ).collect(toList());
            case MEDIOCRE_PROTEIN -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.proteinContent(), 7, 12, 20, 30)
                    ).collect(toList());
            case HIGH_PROTEIN -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.proteinContent(), 20, 30, 88.32, 88.32)
                    ).collect(toList());
        };
    }
}
