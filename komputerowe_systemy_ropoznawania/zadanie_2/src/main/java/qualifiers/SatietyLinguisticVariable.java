package qualifiers;

import database.Meal;
import database.MealDatabase;

import java.util.List;
import java.util.stream.Collectors;

import static qualifiers.MembersipLabel.*;

public class SatietyLinguisticVariable {
    final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(SatietyMembersipLabel label){
        return switch (label){
            case VERY_LOW_CALORIE -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.satiety(), 0, 0,70, 130)
            ).collect(Collectors.toList());
            case LOW_CALORIE -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.satiety(), 100, 170,200, 250)
                    ).collect(Collectors.toList());
            case MEDIUM_CALORIE -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.satiety(), 200, 300,400, 600)
                    ).collect(Collectors.toList());
            case HIGH_CALORIE -> {
                yield  data
                        .stream()
                        .map(meal ->
                                MembershipFunctions.trapezium(meal.satiety(), 500, 550,700, 800)
                        ).collect(Collectors.toList());
            }
            case EXTREMELY_HIGH_CALORIE -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.satiety(), 700, 850,902, 902)
                    ).collect(Collectors.toList());
        };
    }
}
