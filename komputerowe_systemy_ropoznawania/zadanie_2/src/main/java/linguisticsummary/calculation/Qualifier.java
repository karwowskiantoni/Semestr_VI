package linguisticsummary.calculation;

import linguisticsummary.model.Variable;

import java.util.List;
import java.util.stream.Collectors;

public class Qualifier {
    private final List<Variable> variables;

    public Qualifier(List<Variable> variables) {
        this.variables = variables;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public String linguinize() {
        if (variables.size() == 0) {
            return "";
        } else {
            String sentence = " having " + variables.stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
            return sentence.substring(0, sentence.length() - 5);
        }
    }
}