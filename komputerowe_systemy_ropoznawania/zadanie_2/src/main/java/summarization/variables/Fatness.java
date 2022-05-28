package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.trapezium;

public class Fatness {
    public enum Label {
        ALMOST_WITHOUT_FAT, LITTLE_FAT, FAT, HIGH_FAT, EXTREMELY_FAT
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case ALMOST_WITHOUT_FAT -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.fatness(), 0, 0, 5, 10)
                    ).collect(toList());
            case LITTLE_FAT -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.fatness(), 6, 12, 18, 20)
                    ).collect(toList());
            case FAT -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.fatness(), 15, 25, 30, 35)
                    ).collect(toList());
            case HIGH_FAT -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.fatness(), 30, 40, 50, 60)
                    ).collect(toList());
            case EXTREMELY_FAT -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.fatness(), 40, 60, 100, 100)
                    ).collect(toList());
        };
    }
}
