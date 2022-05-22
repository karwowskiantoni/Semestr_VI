import qualification.SatietyLinguisticVariable;

import java.util.List;

import static membership.MembershipLabel.AbsoluteQuantifierMembershipLabel.MORE_THAN_7000;
import static membership.MembershipLabel.SatietyMembershipLabel.EXTREMELY_HIGH_CALORIE;
import static quantification.AbsoluteQuantifier.calculateMembership;

class SummarizationCli {
    public static void main(String... args) {
        List<Double> calc = SatietyLinguisticVariable.calculateMembership(EXTREMELY_HIGH_CALORIE);
        double sigmaCount = calc.stream().mapToDouble(Double::doubleValue).reduce(0, Double::sum);
        System.out.println(calculateMembership(MORE_THAN_7000, sigmaCount));
    }
}

