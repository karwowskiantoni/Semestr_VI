package linguisticsummary;

import linguisticsummary.calculation.Qualifier;
import linguisticsummary.calculation.Summarizer;
import linguisticsummary.calculation.Summary;
import linguisticsummary.database.Initialization;
import linguisticsummary.model.*;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.database.MealLabelDatabase;

import java.util.*;

class LinguisticSummary {
    public static void main(String... args) {
        Initialization.initialize();

        List<Meal> meals = MealDatabase.loadAll();
        List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
        List<MealLabel> allLabels = MealLabelDatabase.loadAll();

        List<Summary> allSummaries = new ArrayList<>();

        for(List<MealLabel> labels : allCombinations(allLabels.stream().limit(10).toList(), 2)) {
            for(Quantifier quantifier: allQuantifiers) {
                allSummaries.add(new Summary(quantifier, new Qualifier(List.of(labels.get(0))), new Summarizer(List.of(labels.get(1))), meals));
            }
        }
        allSummaries.sort(Comparator.comparingDouble(value -> value.measures().getOptimalSummary()));
        Collections.reverse(allSummaries);
        allSummaries.stream().filter(summary -> !Double.isNaN(summary.measures().getOptimalSummary())).limit(20).forEach(System.out::println);
    }

    private static <T> List<T> random(List<T> list, int n) {
        List<T> newList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            newList.add(list.get(random.nextInt(list.size())));
        }
        return newList;
    }

    private static <T> Set<List<T>> allCombinations(List<T> objects, int k) {
        Set<List<T>> subarrays = new HashSet<>();
        recursiveSearch(objects, 0, k, subarrays, new ArrayList<>());
        return subarrays;
    }

    private static <T> void recursiveSearch(List<T> objects, int i, int k, Set<List<T>> subarrays, List<T> out) {
        if (objects.size() == 0 || k > objects.size()) {
            return;
        } else if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            return;
        } for (int j = i; j < objects.size(); j++) {
            out.add(objects.get(j));
            recursiveSearch(objects, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);
        }
    }
}


