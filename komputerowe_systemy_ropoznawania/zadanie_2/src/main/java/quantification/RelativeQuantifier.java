package quantification;

import static membership.MembershipLabel.*;

public class RelativeQuantifier {
    public static double calculateTypeMembership(RelativeQuantifierMembershipLabel label, double sigmaCount, double cardinality){
        //values for relative membership is not yet done, so it's placeholder
        return switch (label){
            case VERY_SMALL_AMOUNT -> 1.0;
            case SMALL_AMOUNT -> 2.0;
            case MEDIUM_AMOUNT -> 3.0;
            case HIGH_AMOUNT -> 4.0;
            case VERY_HIGH_AMOUNT -> 5.0;
        };
    }
}
