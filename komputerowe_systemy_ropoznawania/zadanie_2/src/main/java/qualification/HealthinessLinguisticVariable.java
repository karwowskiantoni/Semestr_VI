package qualification;

import database.Meal;
import database.MealDatabase;
import membership.MembershipLabel.HealthinessMembershipLabel;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static membership.MembershipFunctions.gauss;

public class HealthinessLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(HealthinessMembershipLabel label) {
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
