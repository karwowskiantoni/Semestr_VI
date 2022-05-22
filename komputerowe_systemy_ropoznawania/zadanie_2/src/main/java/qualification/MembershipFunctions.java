package qualification;

import static java.lang.Math.*;

public class MembershipFunctions {

    public static double trapezium(
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

    public static double gauss(double x, double centerPosition, double width) {
        return exp(-((pow(x - centerPosition, 2)) / (2 * pow(width, 2))));
    }
}
