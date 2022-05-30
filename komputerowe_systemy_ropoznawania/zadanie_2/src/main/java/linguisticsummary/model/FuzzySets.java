package linguisticsummary.model;

import linguisticsummary.model.variables.Variable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract public class FuzzySets {
    protected final List<Variable> variables;

    public FuzzySets(List<Variable> variables) {
        this.variables = variables;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public double sigmaCount(List<Meal> meals) {
        return tConorm(meals).stream().mapToDouble(value -> value).sum();
    }

    public double degreeOfFuzziness(List<Meal> meals) {
        return tConorm(meals).stream().mapToDouble(value -> value).filter(value -> value > 0).count() / (meals.size() * 1.0);
    }

    public List<Double> tConorm(List<Meal> meals) {
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
