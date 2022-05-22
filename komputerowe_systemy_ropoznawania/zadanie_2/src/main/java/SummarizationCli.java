import qualification.MembershipLabel;
import qualification.linguisticvariables.SatietyLinguisticVariable;

import java.util.List;

class SummarizationCli {
    public static void main(String... args) {
        List<Double> calc = SatietyLinguisticVariable.calculateMembership(MembershipLabel.SatietyMembershipLabel.EXTREMELY_HIGH_CALORIE);
        int i = 0;
        for (Double mem : calc){
            if(mem != 0){
                i++;
            }
        }
        System.out.println(i);
    }
}

