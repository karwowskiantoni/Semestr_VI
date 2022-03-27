package classification.algorithm;

import classification.model.Label;
import classification.model.Result;

import java.util.List;

public class QualityMeasuresCalculator {

    public static void printResults(List<Result> results) {
        float precision = precisionForSet(results);
        float recall = recallForSet(results);
        System.out.println("Accuracy for data set = " + accuracy(results));
        System.out.println("Precision for data set = " + precisionForSet(results));
        System.out.println("Recall for data set = " + recallForSet(results));
        System.out.println("F1 for data set = " + F1(precision, recall));
    }

    public static float accuracy(List<Result> results) {
        int TP = 0;
        for (Result result : results) {
            if (result.correctLabel() == result.predictedLabel()) {
                TP += 1;
            }
        }
        return TP * 1.0f / results.size();
    }

    public static float precisionForSet(List<Result> results) {
        Label[] labels = Label.values();
        float PPV = 0.0f;
        for (Label label : labels) {
            long occurrences = results
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            float precision = precisionPerClass(results, label);
            PPV += (occurrences * precision) / occurrences;
        }
        return PPV;
    }

    public static float recallForSet(List<Result> results) {
        Label[] labels = Label.values();
        float TPR = 0.0f;
        for (Label label : labels) {
            long occurrences = results
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            float recall = recallPerClass(results, label);
            TPR += (occurrences * recall) / occurrences;
        }
        return TPR;
    }

    public static float F1(float precision, float recall) {
        return 2 * ((recall * precision) / (recall + precision));
    }

    public static float F1(List<Result> results) {
        float PPV = precisionForSet(results);
        float TPR = recallForSet(results);
        return 2 * ((TPR * PPV) / (TPR + PPV));
    }

    public static float precisionPerClass(List<Result> results, Label label) {
        int TP = 0;
        int FP = 0;
        for (Result result : results) {
            if (label == result.correctLabel() && result.correctLabel() == result.predictedLabel()) {
                TP += 1;
            } else if (label != result.correctLabel() && result.correctLabel() != result.predictedLabel()) {
                FP += 1;
            }
        }
        return TP * 1.0f / (TP + FP);
    }

    public static float recallPerClass(List<Result> results, Label label) {
        int TP = 0;
        int FN = 0;
        for (Result result : results) {
            if (result.correctLabel() == label && result.correctLabel() == result.predictedLabel()) {
                TP += 1;
            } else if (label == result.correctLabel()) {
                FN += 1;
            }
        }
        return TP * 1.0f / (TP + FN);
    }

    public static float F1PerClass(List<Result> results, Label label) {
        float PPV = precisionPerClass(results, label);
        float TPR = recallPerClass(results, label);
        return 2 * ((TPR * PPV) / (TPR + PPV));
    }
}
