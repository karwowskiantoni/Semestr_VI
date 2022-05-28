package linguisticsummary;

import java.util.List;
import java.util.stream.Collectors;

public class Summarizer {
    private final List<Variable> variables;

    public Summarizer(List<Variable> variables) {
        this.variables = variables;
    }

    public String linguinize() {
        return variables.stream().map(variable -> variable.getLabel() + " and").collect(Collectors.joining());
    }
}
