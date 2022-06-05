package linguisticsummary.model;

import java.util.List;
import java.util.stream.IntStream;

public class FuzzySets {
    public static double sigmaCount(MealLabel mealLabel, List<Meal> meals) {
        return meals.stream().mapToDouble(mealLabel::membership).sum();
    }

    public static double sigmaCount(List<MealLabel> mealLabels, List<Meal> meals) {
        return tConorm(mealLabels, meals).stream().mapToDouble(value -> value).sum();
    }

    public static double degreeOfFuzziness(MealLabel mealLabel, List<Meal> meals) {
        return support(mealLabel, meals).size() / (meals.size() * 1.0);
    }

    public static double degreeOfFuzziness(List<MealLabel> mealLabels, List<Meal> meals) {
        return support(mealLabels, meals).size() / (meals.size() * 1.0);
    }

    public static List<Double> support(List<MealLabel> mealLabels, List<Meal> meals) {
        return tConorm(mealLabels, meals).stream().filter(value -> value > 0).toList();
    }

    public static List<Double> support(MealLabel mealLabel, List<Meal> meals) {
        return meals.stream().map(mealLabel::membership).filter(value -> value > 0).toList();
    }

    private static List<Double> tConorm(List<MealLabel> mealLabels, List<Meal> meals) {
        List<List<Double>> list = mealLabels
                .stream().map(mealLabel ->
                        meals.stream().map(mealLabel::membership
                        ).toList()
                ).toList();
        return minFromColumns(list);
    }

    private static List<Double> minFromColumns(List<List<Double>> list) {
        return IntStream.range(0, list.get(0).size())
                .mapToObj(i -> list.stream()
                        .mapToDouble(l -> l.get(i))
                        .min()
                        .orElse(0))
                .toList();
    }
}
