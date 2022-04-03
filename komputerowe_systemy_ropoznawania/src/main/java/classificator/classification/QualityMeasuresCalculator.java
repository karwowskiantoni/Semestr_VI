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
        Arrays.stream(Label.values()).forEach(label -> System.out.print(label + ";"));
        System.out.println("data_set");
        Arrays.stream(Label.values()).forEach(label -> System.out.print(precisionsByLabel.get(label) + ";"));
        System.out.println(precisionForSet + ";");
        Arrays.stream(Label.values()).forEach(label -> System.out.print(recallByLabel.get(label) + ";"));
        System.out.println(recallForSet + ";");
        Arrays.stream(Label.values()).forEach(label -> System.out.print(f1ByLabel.get(label) + ";"));
        System.out.println(f1ForSet + ";");
        System.out.println("Accuracy = " + accuracyForSet);
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
        int allOccurrencies = 0;
        for (Label label : labels) {
            long occurrences = results
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            allOccurrencies += occurrences;
            if (occurrences != 0) {
                float precision = precisionByLabel.get(label);
                PPV += (occurrences * precision);
            }
        }
        return PPV/allOccurrencies;
    }

    private static float recallForSet(List<Result> results, Map<Label, Float> recallByLabel) {
        Label[] labels = Label.values();
        float TPR = 0.0f;
        int allOccurrencies = 0;
        for (Label label : labels) {
            long occurrences = results
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            allOccurrencies += occurrences;
            if (occurrences != 0) {
                float recall = recallByLabel.get(label);
                TPR += (occurrences * recall);
            }
        }
        return TPR/allOccurrencies;
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
