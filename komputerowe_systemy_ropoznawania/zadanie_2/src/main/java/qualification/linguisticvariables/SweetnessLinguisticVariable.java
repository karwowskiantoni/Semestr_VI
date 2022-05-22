package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;

import java.util.List;
import java.util.stream.Collectors;

import static qualification.MembershipLabel.SweetnessMembershipLabel;

public class SweetnessLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(SweetnessMembershipLabel label) {
        return switch (label) {
            case NOT_SWEET -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.sweetness(), 0, 0, 1, 2)
                    ).collect(Collectors.toList());
            case SWEET -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.sweetness(), 0.5, 2, 6, 8)
                    ).collect(Collectors.toList());
            case VERY_SWEET -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.sweetness(), 5, 9, 13, 15)
                    ).collect(Collectors.toList());
            case MADE_OF_SUGAR -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.sweetness(), 13, 16, 20, 20)
                    ).collect(Collectors.toList());
        };
    }
}
