package quantification;

import static membership.MembershipFunctions.trapezium;
import static membership.MembershipLabel.AbsoluteQuantifierMembershipLabel;

public class AbsoluteQuantifier {

    public static double calculateMembership(AbsoluteQuantifierMembershipLabel label, double sigmaCount) {
        return switch (label) {
            case LESS_THAN_2000 -> trapezium(sigmaCount, 0, 0, 1700, 1800);
            case ABOUT_2500 -> trapezium(sigmaCount, 1500, 1800, 3400, 3600);
            case ABOUT_5000 -> trapezium(sigmaCount, 3400, 3600, 5200, 5400);
            case ABOUT_6500 -> trapezium(sigmaCount, 5200, 5400, 7000, 7200);
            case MORE_THAN_7000 -> trapezium(sigmaCount, 7000, 7200, 8792, 8792);
        };
    }
}
