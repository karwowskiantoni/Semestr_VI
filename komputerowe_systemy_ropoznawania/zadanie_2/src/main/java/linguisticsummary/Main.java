package linguisticsummary;

import linguisticsummary.database.VariableDatabase;
import linguisticsummary.model.Variable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void findCombinations(List<Variable> variables, int i, int k, Set<List<Variable>> subarrays, List<Variable> out) {
        if (variables.size() == 0 || k > variables.size()) {
            return;
        }

        if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            return;
        }

        for (int j = i; j < variables.size(); j++) {
            out.add(variables.get(j));
            findCombinations(variables, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);
        }
    }

    public static Set<List<Variable>> findCombinations(List<Variable> variables, int k) {
        Set<List<Variable>> subarrays = new HashSet<>();
        findCombinations(variables, 0, k, subarrays, new ArrayList<>());
        return subarrays;
    }

    public static void main(String[] args) {
        int k = 2;
        System.out.println(findCombinations(VariableDatabase.loadAll().stream().limit(5).toList(), k));
    }
}
