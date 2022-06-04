package linguisticsummary.calculation;

import linguisticsummary.model.Meal;
import linguisticsummary.model.LabelFunction;

import java.util.List;
import java.util.stream.IntStream;

public class FuzzySets {
    public static double sigmaCount(LabelFunction<Meal> labelFunction, List<Meal> meals) {
        return meals.stream().mapToDouble(meal -> labelFunction.getMembership().apply(meal)).sum();
    }

    public static double sigmaCount(List<LabelFunction<Meal>> labelFunctions, List<Meal> meals) {
        return tConorm(labelFunctions, meals).stream().mapToDouble(value -> value).sum();
    }

    public static double degreeOfFuzziness(LabelFunction<Meal> labelFunction, List<Meal> meals) {
        return support(labelFunction, meals).size() / (meals.size() * 1.0);
    }

    public static double degreeOfFuzziness(List<LabelFunction<Meal>> labelFunctions, List<Meal> meals) {
        return support(labelFunctions, meals).size() / (meals.size() * 1.0);
    }

    public static List<Double> support(List<LabelFunction<Meal>> labelFunctions, List<Meal> meals) {
        return tConorm(labelFunctions, meals).stream().filter(value -> value > 0).toList();
    }

    public static List<Double> support(LabelFunction<Meal> labelFunction, List<Meal> meals) {
        return meals.stream().map( meal -> labelFunction.getMembership().apply(meal)).filter(value -> value > 0).toList();
    }

    public static List<Double> tConorm(List<LabelFunction<Meal>> labelFunctions, List<Meal> meals) {
        List<List<Double>> list = labelFunctions
                .stream().map(variable ->
                        meals.stream().map(meal ->
                                variable.getMembership().apply(meal)
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
