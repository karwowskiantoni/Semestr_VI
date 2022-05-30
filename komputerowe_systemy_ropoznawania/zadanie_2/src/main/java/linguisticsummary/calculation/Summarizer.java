package linguisticsummary.calculation;

import linguisticsummary.model.Variable;

import java.util.List;
import java.util.stream.Collectors;

public class Summarizer extends FuzzySets {

    public Summarizer(List<Variable> variables) {
        super(variables);
    }

    public String linguinize() {
        String sentence = " are " + getVariables().stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }

}
