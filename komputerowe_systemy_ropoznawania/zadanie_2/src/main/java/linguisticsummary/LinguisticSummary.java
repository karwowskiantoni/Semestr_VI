package linguisticsummary;

import linguisticsummary.calculation.Qualifier;
import linguisticsummary.calculation.Summarizer;
import linguisticsummary.calculation.Summary;
import linguisticsummary.model.*;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.database.VariableDatabase;

import java.util.*;

class LinguisticSummary {
    public static void main(String... args) {
//        Initialization.initialize();

        List<Meal> meals = MealDatabase.loadAll();
        List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
        List<LabelFunction> allLabelFunctions = VariableDatabase.loadAll();

        List<Summary> allSummaries = new ArrayList<>();
        for(List<LabelFunction> labelFunctions : findCombinations(allLabelFunctions.stream().limit(10).toList(), 3)) {
            for(Quantifier quantifier: allQuantifiers) {
                allSummaries.add(new Summary(meals, quantifier, new Qualifier(new ArrayList<>()), new Summarizer(labelFunctions)));
            }
        }
        allSummaries.sort(Comparator.comparingDouble(Summary::optimalSummary));
        Collections.reverse(allSummaries);
        allSummaries.stream().limit(20).forEach(summary -> {
            System.out.println(summary.linguinize());
            System.out.println(summary.measures());
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

    private static void findCombinations(List<LabelFunction> labelFunctions, int i, int k, Set<List<LabelFunction>> subarrays, List<LabelFunction> out) {
        if (labelFunctions.size() == 0 || k > labelFunctions.size()) {
            return;
        } else if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            return;
        } for (int j = i; j < labelFunctions.size(); j++) {
            out.add(labelFunctions.get(j));
            findCombinations(labelFunctions, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);
        }
    }
    private static Set<List<LabelFunction>> findCombinations(List<LabelFunction> labelFunctions, int k) {
        Set<List<LabelFunction>> subarrays = new HashSet<>();
        findCombinations(labelFunctions, 0, k, subarrays, new ArrayList<>());
        return subarrays;
    }

}


