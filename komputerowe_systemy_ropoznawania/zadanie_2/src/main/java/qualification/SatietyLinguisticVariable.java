package qualification;

import database.Meal;
import database.MealDatabase;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static membership.MembershipFunctions.trapezium;
import static membership.MembershipLabel.SatietyMembershipLabel;

public class SatietyLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(SatietyMembershipLabel label) {
        return switch (label) {
            case VERY_LOW_CALORIE -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.satiety(), 0, 0, 70, 130)
                    ).collect(toList());
            case LOW_CALORIE -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.satiety(), 100, 170, 200, 250)
                    ).collect(toList());
            case MEDIUM_CALORIE -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.satiety(), 200, 300, 400, 600)
                    ).collect(toList());
            case HIGH_CALORIE -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.satiety(), 500, 550, 700, 800)
                    ).collect(toList());
            case EXTREMELY_HIGH_CALORIE -> data
                    .stream()
                    .map(meal ->
                            trapezium(meal.satiety(), 700, 850, 902, 902)
                    ).collect(toList());
        };
    }
}
