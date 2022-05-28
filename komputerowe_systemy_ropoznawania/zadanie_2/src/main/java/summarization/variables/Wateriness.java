package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.trapezium;

public class Wateriness {
    public enum Label {
        ALMOST_WITHOUT_WATER, HYDRATING, WATER_RICH, ENTIRELY_WATER
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case ALMOST_WITHOUT_WATER -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.wateriness(), 0, 0, 7, 20)
                    ).collect(toList());
            case HYDRATING -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.wateriness(), 15, 25, 40, 50)
                    ).collect(toList());
            case WATER_RICH -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.wateriness(), 30, 60, 80, 90)
                    ).collect(toList());
            case ENTIRELY_WATER -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.wateriness(), 80, 90, 100, 100)
                    ).collect(toList());
        };
    }
}
