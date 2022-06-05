package linguisticsummary;

import linguisticsummary.model.Qualifier;
import linguisticsummary.model.Summarizer;
import linguisticsummary.summary.*;
import linguisticsummary.database.Initialization;
import linguisticsummary.model.*;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.database.MealLabelDatabase;

import java.util.*;

public class LinguisticSummary {
    public static void main(String... args) {
        Initialization.initialize();
        List<Meal> meals = MealDatabase.loadAll();
        List<Meal> firstHalf = meals.subList(0, 5000);
        List<Meal> secondHalf = meals.subList(5000, meals.size());
        List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
        List<MealLabel> allLabels = MealLabelDatabase.loadAll();

        List<Summary> allSummaries = new ArrayList<>();

        for(List<MealLabel> labels : allCombinations(allLabels.stream().limit(10).toList(), 2)) {
            for(Quantifier quantifier: allQuantifiers) {
                // single entity first form
//                Summarizer summarizer = new Summarizer(labels);
//                allSummaries.add(new SingleEntitySummaryFirstForm(quantifier, summarizer, meals));
                // single entity second form
//                Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
//                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//                allSummaries.add(new SingleEntitySummarySecondForm(quantifier, qualifier, summarizer, meals));

                // multiple entity first form
//                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//                allSummaries.add(new MultipleEntitySummaryFirstForm(quantifier, summarizer, firstHalf, secondHalf));

                // multiple entity second form
//                Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
//                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//                allSummaries.add(new MultipleEntitySummarySecondForm(quantifier, qualifier, summarizer, firstHalf, secondHalf));

                // multiple entity third form
                Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
                allSummaries.add(new MultipleEntitySummaryThirdForm(quantifier, qualifier, summarizer, firstHalf, secondHalf));
            }

            // multiple entity fourth form
//            Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//            allSummaries.add(new MultipleEntitySummaryFourthForm(summarizer, firstHalf, secondHalf));
        }
//        allSummaries.sort(Comparator.comparingDouble(value -> value.measures().getOptimalSummary()));
//        Collections.reverse(allSummaries);
//        allSummaries.stream().filter(singleEntitySummarySecondForm -> !Double.isNaN(singleEntitySummarySecondForm.measures().getOptimalSummary())).limit(20).toList();
        allSummaries.forEach(System.out::println);
    }

    private static <T> List<T> random(List<T> list, int n) {
        List<T> newList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            newList.add(list.get(random.nextInt(list.size())));
        }
        return newList;
    }

    public static <T> Set<List<T>> allCombinations(List<T> objects, int k) {
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


