package classification.algorithm;

import classification.model.Label;
import classification.model.Text;

import java.util.*;
import java.util.stream.Collectors;

public class Classificator {
  private final int K;
  private final List<Text> trainingList;

  public Classificator(List<Text> trainingList, int K) {
    this.K = K;
    this.trainingList = trainingList;
  }

  public void classifyAll(List<Text> textList) {
    textList.forEach(this::classifyOne);
  }

  public Label classifyOne(Text text) {
    return trainingList.stream()
        .collect(
            Collectors.groupingBy(
                Text::correctLabel,
                Collectors.summingDouble(trainingText -> distance(text, trainingText))))
        .entrySet()
        .stream()
        .max(Comparator.comparingDouble(Map.Entry::getValue))
        .map(Map.Entry::getKey).orElse(null);
  }

  public static List<Text> generateTrainingSet(List<Text> textList, int size) {
    textList = new ArrayList<>(textList);
    Collections.shuffle(textList);
    return textList.subList(0, size);
  }

  private float distance(Text firstText, Text secondText) {
    return DistanceMetricsCalculator.euclideanDistance(firstText, secondText);
  }
}
