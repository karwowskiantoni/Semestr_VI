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
        List<Label> allLabels = VariableDatabase.loadAll();

        List<Summary> allSummaries = new ArrayList<>();
        for(List<Label> labels : findCombinations(allLabels.stream().limit(10).toList(), 3)) {
            for(Quantifier quantifier: allQuantifiers) {
                allSummaries.add(new Summary(meals, quantifier, new Qualifier(new ArrayList<>()), new Summarizer(labels)));
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

    private static void findCombinations(List<Label> labels, int i, int k, Set<List<Label>> subarrays, List<Label> out) {
        if (labels.size() == 0 || k > labels.size()) {
            return;
        } else if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            return;
        } for (int j = i; j < labels.size(); j++) {
            out.add(labels.get(j));
            findCombinations(labels, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);
        }
    }
    private static Set<List<Label>> findCombinations(List<Label> labels, int k) {
        Set<List<Label>> subarrays = new HashSet<>();
        findCombinations(labels, 0, k, subarrays, new ArrayList<>());
        return subarrays;
    }

}


