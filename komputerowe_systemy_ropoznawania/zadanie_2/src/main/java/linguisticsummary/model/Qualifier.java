package linguisticsummary.model;

import linguisticsummary.model.variables.Variable;

import java.util.List;
import java.util.stream.Collectors;

public class Qualifier extends FuzzySets {
    public Qualifier(List<Variable> variables) {
        super(variables);
    }

    public String linguinize() {
        if (variables.size() == 0) {
            return "";
        } else {
            String sentence = " having " + getVariables().stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
            return sentence.substring(0, sentence.length() - 5);
        }
    }
}