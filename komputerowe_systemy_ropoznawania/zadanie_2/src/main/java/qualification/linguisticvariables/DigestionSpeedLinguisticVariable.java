package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static qualification.MembershipLabel.DigestionSpeedMembershipLabel;

public class DigestionSpeedLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(DigestionSpeedMembershipLabel label) {
        return switch (label) {
            case SLUGGISH -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.digestionSpeed(), 0, 0.1)
                    ).collect(toList());
            case SLOW -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.digestionSpeed(), 0.3, 0.1)
                    ).collect(toList());
            case STANDARD -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.digestionSpeed(), 0.5, 0.1)
                    ).collect(toList());
            case FAST -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.digestionSpeed(), 0.7, 0.1)
                    ).collect(toList());
            case RAPID -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.gauss(meal.digestionSpeed(), 1, 0.1)
                    ).collect(toList());
        };
    }
}
