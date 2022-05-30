package linguisticsummary.model;

import linguisticsummary.model.variables.Variable;

import java.util.List;
import java.util.stream.Collectors;

public class Summarizer extends LabelMerger{

    public Summarizer(List<Variable> variables) {
        super(variables);
    }

    public String linguinize() {
        String sentence = getVariables().stream().map(variable -> variable.getLabel() + " and ").collect(Collectors.joining());
        return sentence.substring(0, sentence.length() - 5);
    }

}
