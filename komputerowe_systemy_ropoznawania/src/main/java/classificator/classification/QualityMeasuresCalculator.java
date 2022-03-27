package classificator.classification;

import classificator.model.internal.Label;
import classificator.model.internal.Result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QualityMeasuresCalculator {

    public static void printMetrics(List<Result> results) {
        Map<Label, Float> precisionsByLabel = Arrays
                .stream(Label.values())
                .collect(
                        Collectors.toMap(
                                label -> label,
                                label -> precisionPerLabel(results, label)
                        )
                );
        Map<Label, Float> recallByLabel = Arrays
                .stream(Label.values())
                .collect(
                        Collectors.toMap(
                                label -> label,
                                label -> recallPerLabel(results, label)
                        )
                );
        Map<Label, Float> f1ByLabel = Arrays
                .stream(Label.values())
                .collect(
                        Collectors.toMap(
                                label -> label,
                                label -> F1PerLabel(
                                        precisionsByLabel.get(label),
                                        recallByLabel.get(label)
                                )
                        )
                );

        float precisionForSet = precisionForSet(results, precisionsByLabel);
        float recallForSet = recallForSet(results, recallByLabel);
        float f1ForSet = F1(precisionForSet, recallForSet);
        float accuracyForSet = accuracy(results);

        precisionsByLabel.forEach((label, precision) -> System.out.println("Precision for label " + label + " = " + precision));
        recallByLabel.forEach((label, recall) -> System.out.println("Recall for label " + label + " = " + recall));
        f1ByLabel.forEach((label, f1) -> System.out.println("F1 for label " + label + " = " + f1));
        System.out.println("Precision for data set = " + precisionForSet);
        System.out.println("Recall for data set = " + recallForSet);
        System.out.println("F1 for data set = " + f1ForSet);
        System.out.println("Accuracy for data set = " + accuracyForSet);
    }

    private static float accuracy(List<Result> results) {
        int TP = 0;
        for (Result result : results) {
            if (result.correctLabel() == result.predictedLabel()) {
                TP += 1;
            }
        }
        return TP * 1.0f / results.size();
    }

    private static float precisionForSet(List<Result> results, Map<Label, Float> precisionByLabel) {
        Label[] labels = Label.values();
        float PPV = 0.0f;
        for (Label label : labels) {
            long occurrences = results
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            if (occurrences != 0) {
                float precision = precisionByLabel.get(label);
                PPV += (occurrences * precision) / occurrences;
            }
        }
        return PPV;
    }

    private static float recallForSet(List<Result> results, Map<Label, Float> recallByLabel) {
        Label[] labels = Label.values();
        float TPR = 0.0f;
        for (Label label : labels) {
            long occurrences = results
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            if (occurrences != 0) {
                float recall = recallByLabel.get(label);
                TPR += (occurrences * recall) / occurrences;
            }
        }
        return TPR;
    }

    private static float F1(float precision, float recall) {
        return 2 * ((recall * precision) / (recall + precision));
    }

    private static float precisionPerLabel(List<Result> results, Label label) {
        int TP = 0;
        int FP = 0;
        for (Result result : results) {
            if (label == result.correctLabel() && result.correctLabel() == result.predictedLabel()) {
                TP += 1;
            } else if (label != result.correctLabel() && result.correctLabel() != result.predictedLabel()) {
                FP += 1;
            }
        }
        if (TP + FP == 0) {
            return 0;
        }
        return TP * 1.0f / (TP + FP);
    }

    private static float recallPerLabel(List<Result> results, Label label) {
        int TP = 0;
        int FN = 0;
        for (Result result : results) {
            if (result.correctLabel() == label && result.correctLabel() == result.predictedLabel()) {
                TP += 1;
            } else if (label == result.correctLabel()) {
                FN += 1;
            }
        }
        if (TP + FN == 0) {
            return 0;
        }
        return TP * 1.0f / (TP + FN);
    }

    private static float F1PerLabel(float precision, float recall) {
        if (recall + precision == 0) {
            return 0;
        }
        return 2 * ((recall * precision) / (recall + precision));
    }
}
