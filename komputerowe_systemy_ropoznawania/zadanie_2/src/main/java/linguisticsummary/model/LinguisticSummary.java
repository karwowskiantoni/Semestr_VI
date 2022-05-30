package linguisticsummary.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import linguisticsummary.model.math.Functions;
import linguisticsummary.model.variables.Quantifier;
import linguisticsummary.model.variables.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class LinguisticSummary {
    public static void main(String... args) throws JsonProcessingException {
        List<Meal> meals = Database.loadAll();

        List<Variable> variables = List.of(
                new Variable("LITTLE DIGESTED", meal -> Functions.trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("MEDIOCRE DIGESTED", meal -> Functions.trapezium(meal.absorption(), 80, 85, 94, 97)),
                new Variable("FULLY DIGESTED", meal -> Functions.trapezium(meal.absorption(), 95, 97, 99.8, 99.8)),

                new Variable("AVOIDABLE FOR DIABETICS", meal -> Functions.gauss(meal.adaptationForDiabetics(), 0, 0.1)),
                new Variable("NEUTRAL FOR DIABETICS", meal -> Functions.gauss(meal.adaptationForDiabetics(), 0.4, 0.1)),
                new Variable("DESTINED FOR DIABETICS", meal -> Functions.gauss(meal.adaptationForDiabetics(), 1, 0.2)),

                new Variable("NO IMPACT ON DEPRESSION", meal -> Functions.trapezium(meal.antidepressiveness(), 0, 0, 30, 50)),
                new Variable("LITTLE IMPACT ON DEPRESSION", meal -> Functions.trapezium(meal.antidepressiveness(), 40, 60, 80, 100)),
                new Variable("IMPACTFUL ON DEPRESSION", meal -> Functions.trapezium(meal.antidepressiveness(), 80, 100, 110, 120)),
                new Variable("HIGHLY ANTIDEPRESSANT", meal -> Functions.trapezium(meal.antidepressiveness(), 100, 120, 250, 250)),

                new Variable("SLUGGISH DIGESTION", meal -> Functions.gauss(meal.digestionSpeed(), 0, 0.1)),
                new Variable("SLOW DIGESTION", meal -> Functions.gauss(meal.digestionSpeed(), 0.3, 0.1)),
                new Variable("STANDARD DIGESTION", meal -> Functions.gauss(meal.digestionSpeed(), 0.5, 0.1)),
                new Variable("FAST DIGESTION", meal -> Functions.gauss(meal.digestionSpeed(), 0.7, 0.1)),
                new Variable("RAPID DIGESTION", meal -> Functions.gauss(meal.digestionSpeed(), 1, 0.1)),

                new Variable("ALMOST WITHOUT FAT", meal -> Functions.trapezium(meal.fatness(), 0, 0, 5, 10)),
                new Variable("LITTLE FAT", meal -> Functions.trapezium(meal.fatness(), 6, 12, 18, 20)),
                new Variable("FAT", meal -> Functions.trapezium(meal.fatness(), 15, 25, 30, 35)),
                new Variable("HIGH FAT", meal -> Functions.trapezium(meal.fatness(), 30, 40, 50, 60)),
                new Variable("EXTREMELY FAT", meal -> Functions.trapezium(meal.fatness(), 40, 60, 100, 100)),

                new Variable("UNHEALTHY", meal -> Functions.gauss(meal.healthiness(), 0, 0.2)),
                new Variable("HEALTHY", meal -> Functions.gauss(meal.healthiness(), 0.6, 0.13)),
                new Variable("HEALTHFUL", meal -> Functions.gauss(meal.healthiness(), 1, 0.1)),

                new Variable("LITTLE PROTEIN", meal -> Functions.trapezium(meal.proteinContent(), 0, 0, 5, 10)),
                new Variable("MEDIOCRE PROTEIN", meal -> Functions.trapezium(meal.proteinContent(), 7, 12, 20, 30)),
                new Variable("HIGH PROTEIN", meal -> Functions.trapezium(meal.proteinContent(), 20, 30, 88.32, 88.32)),

                new Variable("VERY LOW CALORIE", meal -> Functions.trapezium(meal.satiety(), 0, 0, 70, 130)),
                new Variable("LOW CALORIE", meal -> Functions.trapezium(meal.satiety(), 100, 170, 200, 250)),
                new Variable("MEDIUM CALORIE", meal -> Functions.trapezium(meal.satiety(), 200, 300, 400, 600)),
                new Variable("HIGH CALORIE", meal -> Functions.trapezium(meal.satiety(), 500, 550, 700, 800)),
                new Variable("EXTREMELY HIGH CALORIE", meal -> Functions.trapezium(meal.satiety(), 700, 850, 902, 902)),

                new Variable("NO SWEET", meal -> Functions.trapezium(meal.sweetness(), 0, 0, 1, 2)),
                new Variable("SWEET", meal -> Functions.trapezium(meal.sweetness(), 0.5, 2, 6, 8)),
                new Variable("VERY SWEET", meal -> Functions.trapezium(meal.sweetness(), 5, 9, 13, 15)),
                new Variable("MADE OF SUGAR", meal -> Functions.trapezium(meal.sweetness(), 13, 16, 20, 20)),

                new Variable("ALMOST WITHOUT WATER", meal -> Functions.trapezium(meal.wateriness(), 0, 0, 7, 20)),
                new Variable("HYDRATING", meal -> Functions.trapezium(meal.wateriness(), 15, 25, 40, 50)),
                new Variable("WATER RICH", meal -> Functions.trapezium(meal.wateriness(), 30, 60, 80, 90)),
                new Variable("ENTIRELY WATER", meal -> Functions.trapezium(meal.wateriness(), 80, 90, 100, 100)));

        List<Quantifier> quantifiers = List.of(
                new Quantifier("LESS THAN 2000", membershipDegree -> Functions.trapezium(membershipDegree, 0, 0, 1700, 1800), true),
                new Quantifier("ABOUT 2500", membershipDegree -> Functions.trapezium(membershipDegree, 1500, 1800, 3400, 3600), true),
                new Quantifier("ABOUT 5000", membershipDegree -> Functions.trapezium(membershipDegree, 3400, 3600, 5200, 5400), true),
                new Quantifier("ABOUT 6500", membershipDegree -> Functions.trapezium(membershipDegree, 5200, 5400, 7000, 7200), true),
                new Quantifier("MORE THAN 7000", membershipDegree -> Functions.trapezium(membershipDegree, 7000, 7200, 8792, 8792), true),

                new Quantifier("VERY SMALL AMOUNT", membershipDegree -> Functions.gauss(membershipDegree, 0, 0.1), false),
                new Quantifier("SMALL AMOUNT", membershipDegree -> Functions.gauss(membershipDegree, 0.25, 0.1), false),
                new Quantifier("MEDIUM AMOUNT", membershipDegree -> Functions.gauss(membershipDegree, 0.5, 0.7), false),
                new Quantifier("HIGH AMOUNT", membershipDegree -> Functions.gauss(membershipDegree, 0.75, 0.1), false),
                new Quantifier("VERY HIGH AMOUNT", membershipDegree -> Functions.gauss(membershipDegree, 1, 0.1), false)
        );
        Qualifier qualifier = new Qualifier(getRandom(variables, 1));
        Summarizer summarizer = new Summarizer(getRandom(variables, 1));
        Summary summary = new Summary(getRandom(quantifiers, 1).get(0), qualifier, summarizer);
        System.out.println(summary.linguinize(meals));
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


