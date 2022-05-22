package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;

import java.util.List;
import java.util.stream.Collectors;

import static qualification.MembershipLabel.AdaptationForDiabeticsMembershipLabel;

public class AdaptationForDiabeticsLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(AdaptationForDiabeticsMembershipLabel label) {
        return switch (label) {
            case AVOIDABLE -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.adaptationForDiabetics(), 0, 0.1)
                    ).collect(Collectors.toList());
            case NEUTRAL -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.adaptationForDiabetics(), 0.4, 0.1)
                    ).collect(Collectors.toList());
            case DESTINED_FOR_DIABETICS -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.adaptationForDiabetics(), 1, 0.2)
                    ).collect(Collectors.toList());
        };
    }
}
