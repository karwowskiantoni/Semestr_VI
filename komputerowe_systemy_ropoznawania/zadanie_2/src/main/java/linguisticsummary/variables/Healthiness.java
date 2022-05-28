package linguisticsummary.variables;

import linguisticsummary.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static linguisticsummary.math.Functions.gauss;

public class Healthiness {
    public enum Label {
        UNHEALTHY, HEALTHY, HEALTHFUL
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case UNHEALTHY -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.healthiness(), 0, 0.2)
                    ).collect(toList());
            case HEALTHY -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.healthiness(), 0.6, 0.13)
                    ).collect(toList());
            case HEALTHFUL -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.healthiness(), 1, 0.10)
                    ).collect(toList());
        };
    }
}
