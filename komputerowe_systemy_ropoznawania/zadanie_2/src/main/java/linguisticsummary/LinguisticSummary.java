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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class LinguisticSummary {
    public static void main(String... args) throws IOException, ClassNotFoundException {
//        Initialization.initialize();

        List<Meal> meals = MealDatabase.loadAll();
        List<Quantifier> quantifiers = QuantifierDatabase.loadAll();
        List<Variable> variables = VariableDatabase.loadAll();

        Quantifier quantifier = getRandom(quantifiers, 1).get(0);
        Qualifier qualifier = new Qualifier(getRandom(variables, 1));
        Summarizer summarizer = new Summarizer(getRandom(variables, 1));
        Summary summary = new Summary(quantifier, qualifier, summarizer);
        System.out.println(summary.linguinize(meals));
        System.out.println(summary.measures(meals));
    }

    private static <T> List<T> getRandom(List<T> list, int n) {
        List<T> newList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            newList.add(list.get(random.nextInt(list.size())));
        }
        return newList;
    }
}


