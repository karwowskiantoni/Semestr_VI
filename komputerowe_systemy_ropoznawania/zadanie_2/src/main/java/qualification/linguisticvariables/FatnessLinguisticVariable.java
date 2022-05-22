package qualification.linguisticvariables;

import database.Meal;
import database.MealDatabase;
import qualification.MembershipFunctions;

import java.util.List;
import java.util.stream.Collectors;

import static qualification.MembershipLabel.FatnessMembershipLabel;

public class FatnessLinguisticVariable {
    private final static List<Meal> data = MealDatabase.data();

    public static List<Double> calculateMembership(FatnessMembershipLabel label) {
        return switch (label) {
            case ALMOST_WITHOUT_FAT -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.fatness(), 0, 0, 5, 10)
                    ).collect(Collectors.toList());
            case LITTLE_FAT -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.fatness(), 6, 12, 18, 20)
                    ).collect(Collectors.toList());
            case FAT -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.fatness(), 15, 25, 30, 35)
                    ).collect(Collectors.toList());
            case HIGH_FAT -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.fatness(), 30, 40, 50, 60)
                    ).collect(Collectors.toList());
            case EXTREMELY_FAT -> data
                    .stream()
                    .map(meal ->
                            MembershipFunctions.trapezium(meal.fatness(), 40, 60, 100, 100)
                    ).collect(Collectors.toList());
        };
    }
}
