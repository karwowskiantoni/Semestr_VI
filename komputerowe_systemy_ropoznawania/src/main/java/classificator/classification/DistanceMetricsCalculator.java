package classificator.classification;

import classificator.model.internal.Vector;

public class DistanceMetricsCalculator {
    public static float taxiCabDistance (Vector firstVector, Vector secondVector){
        return 1.0f * firstVector.firstDate() + secondVector.firstDate();
    }

    public static float euclideanDistance (Vector firstVector, Vector secondVector){
        return 1.0f * firstVector.firstDate() + secondVector.firstDate();
    }

    public static float chebyshevDistance (Vector firstVector, Vector secondVector){
        return 1.0f * firstVector.firstDate() + secondVector.firstDate();
    }
}
