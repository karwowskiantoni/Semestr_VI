package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;

import java.util.List;
import java.util.stream.Collectors;

import static qualification.MembershipLabel.ProteinContentMembershipLabel;

public class ProteinContentLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(ProteinContentMembershipLabel label) {
        return switch (label) {
            case LITTLE_PROTEIN -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.proteinContent(), 0, 0, 5, 10)
                    ).collect(Collectors.toList());
            case MEDIOCRE_PROTEIN -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.proteinContent(), 7, 12, 20, 30)
                    ).collect(Collectors.toList());
            case HIGH_PROTEIN -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.proteinContent(), 20, 30, 88.32, 88.32)
                    ).collect(Collectors.toList());
        };
    }
}
