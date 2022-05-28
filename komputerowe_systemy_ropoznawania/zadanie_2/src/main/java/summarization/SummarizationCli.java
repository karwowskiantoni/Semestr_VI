package summarization;

import summarization.variables.Satiety;

import java.util.List;

import static summarization.quantification.AbsoluteQuantifier.Label.MORE_THAN_7000;
import static summarization.quantification.AbsoluteQuantifier.membership;
import static summarization.variables.Satiety.Label.EXTREMELY_HIGH_CALORIE;

class SummarizationCli {
    public static void main(String... args) {
        List<Double> calc = Satiety.membership(Database.loadAll(), EXTREMELY_HIGH_CALORIE);
        double sigmaCount = calc.stream().mapToDouble(Double::doubleValue).reduce(0, Double::sum);
        System.out.println(membership(MORE_THAN_7000, sigmaCount));
    }
}

