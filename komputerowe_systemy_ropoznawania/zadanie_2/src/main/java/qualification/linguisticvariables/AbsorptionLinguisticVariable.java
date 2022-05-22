package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static qualification.MembershipLabel.AbsorptionMembershipLabel;

public class AbsorptionLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(AbsorptionMembershipLabel label) {
        return switch (label) {
            case LITTLE_DIGESTED -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.absorption(), 80, 80, 83, 85)
                    ).collect(toList());
            case MEDIOCRE_DIGESTED -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.absorption(), 80, 85, 94, 97)
                    ).collect(toList());
            case FULLY_DIGESTED -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.absorption(), 95, 97, 99.8, 99.8)
                    ).collect(toList());
        };
    }
}
