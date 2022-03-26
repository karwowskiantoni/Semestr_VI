package classification.algorithm;

import classification.model.Text;

public class DistanceMetricsCalculator {
    public static float taxiCabDistance (Text firstText, Text secondText){
        return 1.0f * firstText.vector().firstDate() + secondText.vector().firstDate();
    }

    public static float euclideanDistance (Text firstText, Text secondText){
        return 1.0f * firstText.vector().firstDate() + secondText.vector().firstDate();
    }

    public static float chebyshevDistance (Text firstText, Text secondText){
        return 1.0f * firstText.vector().firstDate() + secondText.vector().firstDate();
    }
}
