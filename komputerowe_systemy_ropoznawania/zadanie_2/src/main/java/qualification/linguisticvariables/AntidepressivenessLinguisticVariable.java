package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;
import qualification.MembershipLabel.AntidepressivenessMembershipLabel;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class AntidepressivenessLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(AntidepressivenessMembershipLabel label) {
        return switch (label) {
            case NO_IMPACT_ON_DEPRESSION -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.antidepressiveness(), 0, 0, 30, 50)
                    ).collect(toList());
            case LITTLE_IMPACT_ON_DEPRESSION -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.antidepressiveness(), 40, 60, 80, 100)
                    ).collect(toList());
            case IMPACTFUL_ON_DEPRESSION -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.antidepressiveness(), 80, 100, 110, 120)
                    ).collect(toList());
            case HIGHLY_ANTIDEPRESSANT -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.antidepressiveness(), 100, 120, 250, 250)
                    ).collect(toList());
        };
    }
}
