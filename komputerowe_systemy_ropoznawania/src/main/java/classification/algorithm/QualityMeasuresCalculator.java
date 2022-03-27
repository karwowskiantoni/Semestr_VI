package classification.algorithm;

import classification.model.Label;
import classification.model.Text;

import java.util.List;

public class QualityMeasuresCalculator {

    public static void results(List<Text> textList) {
        float precision = precisionForSet(textList);
        float recall = recallForSet(textList);
        System.out.println("Accuracy for data set = " + accuracy(textList));
        System.out.println("Precision for data set = " + precisionForSet(textList));
        System.out.println("Recall for data set = " + recallForSet(textList));
        System.out.println("F1 for data set = " + F1(precision, recall));
    }

    public static float accuracy(List<Text> textList) {
        int TP = 0;
        for (Text text : textList) {
            if (text.correctLabel() == text.predictedLabel()) {
                TP += 1;
            }
        }
        return TP * 1.0f / textList.size();
    }

    public static float precisionForSet(List<Text> textList) {
        Label[] labels = Label.values();
        float PPV = 0.0f;
        for (Label label : labels) {
            long occurrences = textList
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            float precision = precisionPerClass(textList, label);
            PPV += (occurrences * precision) / occurrences;
        }
        return PPV;
    }

    public static float recallForSet(List<Text> textList) {
        Label[] labels = Label.values();
        float TPR = 0.0f;
        for (Label label : labels) {
            long occurrences = textList
                    .stream()
                    .filter(text -> text.correctLabel() == label)
                    .count();
            float recall = recallPerClass(textList, label);
            TPR += (occurrences * recall) / occurrences;
        }
        return TPR;
    }

    public static float F1(float precision, float recall) {
        return 2 * ((recall * precision) / (recall + precision));
    }

    public static float F1(List<Text> textList) {
        float PPV = precisionForSet(textList);
        float TPR = recallForSet(textList);
        return 2 * ((TPR * PPV) / (TPR + PPV));
    }

    public static float precisionPerClass(List<Text> textList, Label label) {
        int TP = 0;
        int FP = 0;
        for (Text text : textList) {
            if (label == text.correctLabel() && text.correctLabel() == text.predictedLabel()) {
                TP += 1;
            } else if (label != text.correctLabel() && text.correctLabel() != text.predictedLabel()) {
                FP += 1;
            }
        }
        return TP * 1.0f / (TP + FP);
    }

    public static float recallPerClass(List<Text> textList, Label label) {
        int TP = 0;
        int FN = 0;
        for (Text text : textList) {
            if (text.correctLabel() == label && text.correctLabel() == text.predictedLabel()) {
                TP += 1;
            } else if (label == text.correctLabel()) {
                FN += 1;
            }
        }
        return TP * 1.0f / (TP + FN);
    }

    public static float F1PerClass(List<Text> textList, Label label) {
        float PPV = precisionPerClass(textList, label);
        float TPR = recallPerClass(textList, label);
        return 2 * ((TPR * PPV) / (TPR + PPV));
    }
}
