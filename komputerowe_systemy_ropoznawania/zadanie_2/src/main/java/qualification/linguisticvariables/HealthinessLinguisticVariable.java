package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;
import qualification.MembershipLabel.HealthinessMembershipLabel;

import java.util.List;
import java.util.stream.Collectors;

public class HealthinessLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(HealthinessMembershipLabel label) {
        return switch (label) {
            case UNHEALTHY -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.healthiness(), 0, 0.2)
                    ).collect(Collectors.toList());
            case HEALTHY -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.healthiness(), 0.6, 0.13)
                    ).collect(Collectors.toList());
            case HEALTHFUL -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.healthiness(), 1, 0.10)
                    ).collect(Collectors.toList());
        };
    }
}
