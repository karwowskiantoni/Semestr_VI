package linguisticsummary;

import java.util.List;

import static linguisticsummary.math.Functions.trapezium;

class LinguisticSummary {
    public static void main(String... args) {
        List<Variable> variables = List.of(
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("little digested", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
        );
    }
}

