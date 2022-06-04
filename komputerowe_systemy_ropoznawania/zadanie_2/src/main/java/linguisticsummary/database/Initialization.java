package linguisticsummary.database;

import linguisticsummary.model.Meal;
import linguisticsummary.model.MealLabel;
import linguisticsummary.model.Quantifier;

import java.util.List;

import static linguisticsummary.model.Label.FunctionType.GAUSS;
import static linguisticsummary.model.Label.FunctionType.TRAPEZIUM;

public class Initialization {
    public static void initialize() {
        List<Double> depressivenessDomain = List.of(0.0, 250.0);
        List<Double> digestionDomain = List.of(0.0, 1.0);
        List<Double> fatnessDomain = List.of(0.0, 100.0);
        List<Double> healthinessDomain = List.of(0.0, 1.0);
        List<Double> proteinDomain = List.of(0.0, 80.0);
        List<Double> calorieDomain = List.of(0.0, 902.0);
        List<Double> sweetDomain = List.of(0.0, 20.0);
        List<Double> waterDomain = List.of(0.0, 100.0);

        List<MealLabel> mealLabels = List.of(
                new MealLabel("LITTLE DIGESTED", TRAPEZIUM, List.of(80.0, 80.0, 83.0, 85.0), List.of(80.0, 85.0), Meal::absorption),
                new MealLabel("MEDIOCRE DIGESTED", TRAPEZIUM, List.of(80.0, 85.0, 94.0, 97.0), List.of(80.0, 96.0), Meal::absorption),
                new MealLabel("FULLY DIGESTED", TRAPEZIUM, List.of(95.0, 97.0, 99.8, 99.8), List.of(95.0, 100.0), Meal::absorption),

                new MealLabel("AVOIDABLE FOR DIABETICS", GAUSS, List.of(0.0, 0.1), List.of(0.0, 0.35), Meal::adaptationForDiabetics),
                new MealLabel("NEUTRAL FOR DIABETICS", GAUSS, List.of(0.4, 0.1), List.of(0.1, 0.67), Meal::adaptationForDiabetics),
                new MealLabel("DESTINED FOR DIABETICS", GAUSS, List.of(1.0, 0.2), List.of(0.3, 1.0), Meal::adaptationForDiabetics),

                new MealLabel("NO IMPACT ON DEPRESSION", TRAPEZIUM, List.of(0.0, 0.0, 30.0, 50.0), List.of(0.0, 50.0), Meal::antidepressiveness),
                new MealLabel("LITTLE IMPACT ON DEPRESSION", TRAPEZIUM, List.of(40.0, 60.0, 80.0, 100.0), List.of(0.0, 50.0), Meal::antidepressiveness),
                new MealLabel("IMPACTFUL ON DEPRESSION", TRAPEZIUM, List.of( 80.0, 100.0, 110.0, 120.0), List.of(0.0, 50.0), Meal::antidepressiveness),
                new MealLabel("HIGHLY ANTIDEPRESSANT", TRAPEZIUM, List.of( 100.0, 120.0, 250.0, 250.0), List.of(0.0, 50.0), Meal::antidepressiveness),


                new MealLabel("SLUGGISH DIGESTION", GAUSS, List.of(0.0, 0.1), digestionDomain, Meal::digestionSpeed),
                new MealLabel("SLOW DIGESTION", GAUSS, List.of(0.3, 0.1), digestionDomain, Meal::digestionSpeed),
                new MealLabel("STANDARD DIGESTION", GAUSS, List.of(0.5, 0.1), digestionDomain, Meal::digestionSpeed),
                new MealLabel("FAST DIGESTION", GAUSS, List.of(0.7, 0.1), digestionDomain, Meal::digestionSpeed),
                new MealLabel("RAPID DIGESTION", GAUSS, List.of(1.0, 0.1), digestionDomain, Meal::digestionSpeed),

                new MealLabel("ALMOST WITHOUT FAT", TRAPEZIUM, List.of(0.0, 0.0, 5.0, 10.0), fatnessDomain, Meal::fatness),
                new MealLabel("LITTLE FAT", TRAPEZIUM, List.of(6.0, 12.0, 18.0, 20.0), fatnessDomain, Meal::fatness),
                new MealLabel("FAT", TRAPEZIUM, List.of(15.0, 25.0, 30.0, 35.0), fatnessDomain, Meal::fatness),
                new MealLabel("HIGH FAT", TRAPEZIUM, List.of(30.0, 40.0, 50.0, 60.0), fatnessDomain, Meal::fatness),
                new MealLabel("EXTREMELY FAT", TRAPEZIUM, List.of(40.0, 60.0, 100.0, 100.0), fatnessDomain, Meal::fatness),

                new MealLabel("UNHEALTHY", GAUSS, List.of(0.0, 0.2), healthinessDomain, Meal::healthiness),
                new MealLabel("HEALTHY", GAUSS, List.of(0.6, 0.13), healthinessDomain, Meal::healthiness),
                new MealLabel("HEALTHFUL", GAUSS, List.of(1.0, 0.1), healthinessDomain, Meal::healthiness),

                new MealLabel("LITTLE PROTEIN", TRAPEZIUM, List.of(0.0, 0.0, 5.0, 10.0), proteinDomain, Meal::proteinContent),
                new MealLabel("MEDIOCRE PROTEIN", TRAPEZIUM, List.of(7.0, 12.0, 20.0, 30.0), proteinDomain, Meal::proteinContent),
                new MealLabel("HIGH PROTEIN", TRAPEZIUM, List.of(20.0, 30.0, 88.32, 88.32), proteinDomain, Meal::proteinContent),

                new MealLabel("VERY LOW CALORIE", TRAPEZIUM, List.of(0.0, 0.0, 70.0, 130.0), calorieDomain, Meal::satiety),
                new MealLabel("LOW CALORIE", TRAPEZIUM, List.of(100.0, 170.0, 200.0, 250.0), calorieDomain, Meal::satiety),
                new MealLabel("MEDIUM CALORIE", TRAPEZIUM, List.of(200.0, 300.0, 400.0, 600.0), calorieDomain, Meal::satiety),
                new MealLabel("HIGH CALORIE", TRAPEZIUM, List.of(500.0, 550.0, 700.0, 800.0), calorieDomain, Meal::satiety),
                new MealLabel("EXTREMELY HIGH CALORIE", TRAPEZIUM, List.of(700.0, 850.0, 902.0, 902.0), calorieDomain, Meal::satiety),

                new MealLabel("NO SWEET", TRAPEZIUM, List.of(0.0, 0.0, 1.0, 2.0), sweetDomain, Meal::sweetness),
                new MealLabel("SWEET", TRAPEZIUM, List.of(0.5, 2.0, 6.0, 8.0), sweetDomain, Meal::sweetness),
                new MealLabel("VERY SWEET", TRAPEZIUM, List.of(5.0, 9.0, 13.0, 15.0), sweetDomain, Meal::sweetness),
                new MealLabel("MADE OF SUGAR", TRAPEZIUM, List.of(13.0, 16.0, 20.0, 20.0), sweetDomain, Meal::sweetness),


                new MealLabel("ALMOST WITHOUT WATER", TRAPEZIUM, List.of(0.0, 0.0, 7.0, 20.0), waterDomain, Meal::wateriness),
                new MealLabel("HYDRATING", TRAPEZIUM, List.of(15.0, 25.0, 40.0, 50.0), waterDomain, Meal::wateriness),
                new MealLabel("WATER RICH", TRAPEZIUM, List.of(30.0, 60.0, 80.0, 90.0), waterDomain, Meal::wateriness),
                new MealLabel("ENTIRELY WATER", TRAPEZIUM, List.of(80.0, 90.0, 100.0, 100.0), waterDomain, Meal::wateriness)
        );

        List<Quantifier> quantifiers = List.of(
                new Quantifier( "LESS THAN 2000", TRAPEZIUM, List.of(0.0, 0.0, 1700.0, 1800.0), List.of(0.0, 1800.0), true),
                new Quantifier( "ABOUT 2500", TRAPEZIUM, List.of(1500.0, 1800.0, 3400.0, 3600.0), List.of(1500.0, 3600.0), true),
                new Quantifier( "ABOUT 5000", TRAPEZIUM, List.of(3400.0, 3600.0, 5200.0, 5400.0), List.of(3400.0, 5400.0), true),
                new Quantifier( "ABOUT 6500", TRAPEZIUM, List.of(5200.0, 5400.0, 7000.0, 7200.0), List.of(5200.0, 7200.0), true),
                new Quantifier( "MORE THAN 7000", TRAPEZIUM, List.of(7000.0, 7200.0, 8792.0, 8792.0), List.of(7000.0, 8792.0), true),

                new Quantifier( "VERY SMALL AMOUNT", GAUSS, List.of(0.0, 0.1), List.of(0.0, 0.35), false),
                new Quantifier( "SMALL AMOUNT", GAUSS, List.of(0.25, 0.1), List.of(0.0, 0.55), false),
                new Quantifier( "MEDIUM AMOUNT", GAUSS, List.of(0.5, 0.7), List.of(0.25, 0.75), false),
                new Quantifier( "HIGH AMOUNT", GAUSS, List.of(0.75, 0.1), List.of(0.4, 0.35), false),
                new Quantifier( "VERY HIGH AMOUNT", GAUSS, List.of(1.0, 0.1), List.of(0.65, 1.0), false)
                );
        MealLabelsDatabase.saveAll(mealLabels);
        QuantifierDatabase.saveAll(quantifiers);
    }

}
