package linguisticsummary.calculation;

import linguisticsummary.model.Meal;
import linguisticsummary.model.Variable;

import java.util.List;
import java.util.stream.IntStream;

public class FuzzySets {
    public static double sigmaCount(Variable variable, List<Meal> meals) {
        return meals.stream().mapToDouble(meal -> variable.getMembership().apply(meal)).sum();
    }

    public static double sigmaCount(List<Variable> variables, List<Meal> meals) {
        return tConorm(variables, meals).stream().mapToDouble(value -> value).sum();
    }

    public static double degreeOfFuzziness(Variable variable, List<Meal> meals) {
        return meals.stream().mapToDouble(meal -> variable.getMembership().apply(meal)).filter(value -> value > 0).count() / (meals.size() * 1.0);
    }

    public static double degreeOfFuzziness(List<Variable> variables, List<Meal> meals) {
        return tConorm(variables, meals).stream().mapToDouble(value -> value).filter(value -> value > 0).count() / (meals.size() * 1.0);
    }

    public static long support(List<Double> conorm) {
        return conorm.stream().filter(value -> value > 0).count();
    }

    public static List<Double> tConorm(List<Variable> variables, List<Meal> meals) {
        List<List<Double>> list = variables
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
