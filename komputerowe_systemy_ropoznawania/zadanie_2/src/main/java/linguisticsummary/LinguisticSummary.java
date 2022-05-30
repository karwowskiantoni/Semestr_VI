package linguisticsummary;

import linguisticsummary.calculation.Qualifier;
import linguisticsummary.calculation.Summarizer;
import linguisticsummary.calculation.Summary;
import linguisticsummary.database.Initialization;
import linguisticsummary.model.*;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.database.VariableDatabase;

import java.io.IOException;
import java.util.*;

class LinguisticSummary {
    public static void main(String... args) {
//        Initialization.initialize();

        List<Meal> meals = MealDatabase.loadAll();
        List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
        List<Variable> allVariables = VariableDatabase.loadAll();

        List<Summary> allSummaries = new ArrayList<>();
        for(List<Variable> variables: findCombinations(allVariables.stream().limit(10).toList(), 3)) {
            for(Quantifier quantifier: allQuantifiers) {
                allSummaries.add(new Summary(quantifier, new Qualifier(new ArrayList<>()), new Summarizer(variables)));
            }
        }
        allSummaries.sort(Comparator.comparingDouble(o -> o.optimalSummary(meals)));
        Collections.reverse(allSummaries);
        allSummaries.stream().limit(20).forEach(summary -> {
            System.out.println(summary.linguinize(meals));
            System.out.println(summary.measures(meals));
        });
    }

    private static <T> List<T> getRandom(List<T> list, int n) {
        List<T> newList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            newList.add(list.get(random.nextInt(list.size())));
        }
        return newList;
    }

    private static void findCombinations(List<Variable> variables, int i, int k, Set<List<Variable>> subarrays, List<Variable> out) {
        if (variables.size() == 0 || k > variables.size()) {
            return;
        } else if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            return;
        } for (int j = i; j < variables.size(); j++) {
            out.add(variables.get(j));
            findCombinations(variables, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);
        }
    }
    private static Set<List<Variable>> findCombinations(List<Variable> variables, int k) {
        Set<List<Variable>> subarrays = new HashSet<>();
        findCombinations(variables, 0, k, subarrays, new ArrayList<>());
        return subarrays;
    }

}


