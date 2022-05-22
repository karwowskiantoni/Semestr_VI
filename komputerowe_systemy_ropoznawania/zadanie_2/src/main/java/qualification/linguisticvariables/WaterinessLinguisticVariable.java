package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;

import java.util.List;
import java.util.stream.Collectors;

import static qualification.MembershipLabel.*;

public class WaterinessLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(WaterinessMembershipLabel label) {
        return switch (label) {
            case ALMOST_WITHOUT_WATER -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.wateriness(), 0, 0, 7, 20)
                    ).collect(Collectors.toList());
            case HYDRATING -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.wateriness(), 15, 25, 40, 50)
                    ).collect(Collectors.toList());
            case WATER_RICH -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.wateriness(), 30, 60, 80, 90)
                    ).collect(Collectors.toList());
            case ENTIRELY_WATER -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.wateriness(), 80, 90, 100, 100)
                    ).collect(Collectors.toList());
        };
    }
}
