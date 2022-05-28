package summarization.variables;

import summarization.Meal;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static summarization.math.Functions.gauss;

public class AdaptationForDiabetics {
    public enum Label {
        AVOIDABLE, NEUTRAL, DESTINED_FOR_DIABETICS
    }

    public static List<Double> membership(List<Meal> data, Label label) {
        return switch (label) {
            case AVOIDABLE -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.adaptationForDiabetics(), 0, 0.1)
                    ).collect(toList());
            case NEUTRAL -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.adaptationForDiabetics(), 0.4, 0.1)
                    ).collect(toList());
            case DESTINED_FOR_DIABETICS -> data
                    .stream()
                    .map(meal ->
                            gauss(meal.adaptationForDiabetics(), 1, 0.2)
                    ).collect(toList());
        };
    }
}
