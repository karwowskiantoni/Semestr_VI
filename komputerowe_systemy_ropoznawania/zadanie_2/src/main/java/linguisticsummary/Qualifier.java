package linguisticsummary;

import linguisticsummary.variables.Variable;

import java.util.List;
import java.util.stream.Collectors;

public class Qualifier extends LabelMerger {
    public Qualifier(List<Variable> variables) {
        super(variables);
    }

    public String linguinize() {
        String sentence = " having " +
                getVariables().stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }
}
