package linguisticsummary.database;

import javafx.util.Pair;
import linguisticsummary.model.Quantifier;
import linguisticsummary.model.Variable;

import java.util.List;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class Initialization {
    public static void initialize() {
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
                new Quantifier(new Pair(0.0,1800.0),List.of(0.0, 0.0, 1700.0, 1800.0), "LESS THAN 2000", membershipDegree -> trapezium(membershipDegree, 0, 0, 1700, 1800), true, false),
                new Quantifier(new Pair(1500.0,3600.0), List.of(1500.0, 1800.0, 3400.0, 3600.0), "ABOUT 2500", membershipDegree -> trapezium(membershipDegree, 1500, 1800, 3400, 3600), true, false),
                new Quantifier(new Pair(3400.0,5400.0), List.of(3400.0, 3600.0, 5200.0, 5400.0),"ABOUT 5000", membershipDegree -> trapezium(membershipDegree, 3400, 3600, 5200, 5400), true, false),
                new Quantifier(new Pair(5200.0,7200.0), List.of(5200.0, 5400.0, 7000.0, 7200.0), "ABOUT 6500", membershipDegree -> trapezium(membershipDegree, 5200, 5400, 7000, 7200), true,false),
                new Quantifier(new Pair(7000.0,8792.0), List.of(7000.0, 7200.0, 8792.0, 8792.0), "MORE THAN 7000", membershipDegree -> trapezium(membershipDegree, 7000, 7200, 8792, 8792), true, false),

                new Quantifier(new Pair(0.0,0.35),List.of(0.0, 0.1),"VERY SMALL AMOUNT", membershipDegree -> gauss(membershipDegree, 0, 0.1), false, true),
                new Quantifier(new Pair(0.0,0.55),List.of(0.25, 0.1),"SMALL AMOUNT", membershipDegree -> gauss(membershipDegree, 0.25, 0.1), false, true),
                new Quantifier(new Pair(0.25,0.75),List.of(0.5, 0.7),"MEDIUM AMOUNT", membershipDegree -> gauss(membershipDegree, 0.5, 0.7), false, true),
                new Quantifier(new Pair(0.4,1.0),List.of(0.75, 0.1),"HIGH AMOUNT", membershipDegree -> gauss(membershipDegree, 0.75, 0.1), false, true),
                new Quantifier(new Pair(0.65,1.0),List.of(1.0, 0.1),"VERY HIGH AMOUNT", membershipDegree -> gauss(membershipDegree, 1, 0.1), false, true)
        );
        VariableDatabase.saveAll(variables);
        QuantifierDatabase.saveAll(quantifiers);
    }

    private static double trapezium(
            double x,
            double begin,
            double firstFold,
            double secondFold,
            double end) {
        if (x < begin) {
            return 0;
        } else if (begin <= x && x < firstFold) {
            return (x - begin) / (firstFold - begin);
        } else if (firstFold <= x && x < secondFold) {
            return 1;
        } else if (secondFold <= x && x < end) {
            return (end - x) / (end - secondFold);
        } else return 0;
    }

    private static double gauss(double x, double centerPosition, double width) {
        return exp(-((pow(x - centerPosition, 2)) / (2 * pow(width, 2))));
    }
}
