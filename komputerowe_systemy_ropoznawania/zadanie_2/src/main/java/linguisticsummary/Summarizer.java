package linguisticsummary;

import linguisticsummary.variables.Variable;

import java.util.List;
import java.util.stream.Collectors;

public class Summarizer {
    private final List<Variable> variables;

    public Summarizer(List<Variable> variables) {
        this.variables = variables;
    }

    public String linguinize() {
        String sentence = variables.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }

    public double sigmaCount(List<Meal> meals) {
        List<List<Double>> memberships = variables.stream().map(variable ->
                meals.stream().map(meal ->
                        variable.getMembership().apply(meal)
                ).collect(Collectors.toList())
        ).toList();

        double sigmaCount = 0;

        for (int i = 0; i < meals.size(); i++) {
            double minimum = 1;
            for (List<Double> membership : memberships) {
                if (minimum > membership.get(i)) {
                    minimum = membership.get(i);
                }
            }
            sigmaCount += minimum;
        }

        return sigmaCount;
    }
}
