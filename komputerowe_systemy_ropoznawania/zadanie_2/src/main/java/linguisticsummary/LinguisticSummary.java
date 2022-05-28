package linguisticsummary;

import linguisticsummary.variables.Quantifier;
import linguisticsummary.variables.Variable;

import java.util.List;

import static linguisticsummary.math.Functions.gauss;
import static linguisticsummary.math.Functions.trapezium;

class LinguisticSummary {
    public static void main(String... args) {
        List<Variable> variables = List.of(
                new Variable("LITTLE DIGESTED", meal -> trapezium(meal.absorption(), 80, 80, 83, 85)),
                new Variable("MEDIOCRE DIGESTED", meal -> trapezium(meal.absorption(), 80, 85, 94, 97)),
                new Variable("FULLY DIGESTED", meal -> trapezium(meal.absorption(), 95, 97, 99.8, 99.8)),

                new Variable("AVOIDABLE FOR DIABETICS", meal -> gauss(meal.adaptationForDiabetics(), 0, 0.1)),
                new Variable("NEUTRAL FOR DIABETICS", meal -> gauss(meal.adaptationForDiabetics(), 0.4, 0.1)),
                new Variable("DESTINED FOR DIABETICS", meal -> gauss(meal.adaptationForDiabetics(), 1, 0.2)),

                new Variable("NO IMPACT ON DEPRESSION", meal -> trapezium(meal.antidepressiveness(), 0, 0, 30, 50)),
                new Variable("LITTLE IMPACT ON DEPRESSION", meal -> trapezium(meal.antidepressiveness(), 40, 60, 80, 100)),
                new Variable("IMPACTFUL ON DEPRESSION", meal -> trapezium(meal.antidepressiveness(), 80, 100, 110, 120)),
                new Variable("HIGHLY ANTIDEPRESSANT", meal -> trapezium(meal.antidepressiveness(), 100, 120, 250, 250)),

                new Variable("SLUGGISH DIGESTION", meal -> gauss(meal.digestionSpeed(), 0, 0.1)),
                new Variable("SLOW DIGESTION", meal -> gauss(meal.digestionSpeed(), 0.3, 0.1)),
                new Variable("STANDARD DIGESTION", meal -> gauss(meal.digestionSpeed(), 0.5, 0.1)),
                new Variable("FAST DIGESTION", meal -> gauss(meal.digestionSpeed(), 0.7, 0.1)),
                new Variable("RAPID DIGESTION", meal -> gauss(meal.digestionSpeed(), 1, 0.1)),

                new Variable("ALMOST WITHOUT FAT", meal -> trapezium(meal.fatness(), 0, 0, 5, 10)),
                new Variable("LITTLE FAT", meal -> trapezium(meal.fatness(), 6, 12, 18, 20)),
                new Variable("FAT", meal -> trapezium(meal.fatness(), 15, 25, 30, 35)),
                new Variable("HIGH FAT", meal -> trapezium(meal.fatness(), 30, 40, 50, 60)),
                new Variable("EXTREMELY FAT", meal -> trapezium(meal.fatness(), 40, 60, 100, 100)),

                new Variable("UNHEALTHY", meal -> gauss(meal.healthiness(), 0, 0.2)),
                new Variable("HEALTHY", meal -> gauss(meal.healthiness(), 0.6, 0.13)),
                new Variable("HEALTHFUL", meal -> gauss(meal.healthiness(), 1, 0.1)),

                new Variable("LITTLE PROTEIN", meal -> trapezium(meal.proteinContent(), 0, 0, 5, 10)),
                new Variable("MEDIOCRE PROTEIN", meal -> trapezium(meal.proteinContent(), 7, 12, 20, 30)),
                new Variable("HIGH PROTEIN", meal -> trapezium(meal.proteinContent(), 20, 30, 88.32, 88.32)),

                new Variable("VERY LOW CALORIE", meal -> trapezium(meal.satiety(), 0, 0, 70, 130)),
                new Variable("LOW CALORIE", meal -> trapezium(meal.satiety(), 100, 170, 200, 250)),
                new Variable("MEDIUM CALORIE", meal -> trapezium(meal.satiety(), 200, 300, 400, 600)),
                new Variable("HIGH CALORIE", meal -> trapezium(meal.satiety(), 500, 550, 700, 800)),
                new Variable("EXTREMELY HIGH CALORIE", meal -> trapezium(meal.satiety(), 700, 850, 902, 902)),

                new Variable("NO SWEET", meal -> trapezium(meal.sweetness(), 0, 0, 1, 2)),
                new Variable("SWEET", meal -> trapezium(meal.sweetness(), 0.5, 2, 6, 8)),
                new Variable("VERY SWEET", meal -> trapezium(meal.sweetness(), 5, 9, 13, 15)),
                new Variable("MADE OF SUGAR", meal -> trapezium(meal.sweetness(), 13, 16, 20, 20)),

                new Variable("ALMOST WITHOUT WATER", meal -> trapezium(meal.wateriness(), 0, 0, 7, 20)),
                new Variable("HYDRATING", meal -> trapezium(meal.wateriness(), 15, 25, 40, 50)),
                new Variable("WATER RICH", meal -> trapezium(meal.wateriness(), 30, 60, 80, 90)),
                new Variable("ENTIRELY WATER", meal -> trapezium(meal.wateriness(), 80, 90, 100, 100)));

        List<Quantifier> quantifiers = List.of(
                new Quantifier("LESS THAN 2000", (meals) -> 0.0),
                new Quantifier("ABOUT 2500", (meals) -> 0.0),
                new Quantifier("ABOUT 5000", (meals) -> 0.0),
                new Quantifier("ABOUT 6500", (meals) -> 0.0),
                new Quantifier("MORE THAN 7000", (meals) -> 0.0),

                new Quantifier("VERY SMALL AMOUNT", (meals) -> 0.0),
                new Quantifier("SMALL AMOUNT", (meals) -> 0.0),
                new Quantifier("MEDIUM AMOUNT", (meals) -> 0.0),
                new Quantifier("HIGH AMOUNT", (meals) -> 0.0),
                new Quantifier("VERY HIGH AMOUNT", (meals) -> 0.0)
        );
    }
}

