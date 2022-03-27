package classification.algorithm;

import classification.model.Label;
import classification.model.Result;
import classification.model.Text;

import java.util.*;
import java.util.stream.Collectors;

public class Classificator {

  public static List<Result> classifyAll(List<Text> texts, List<Text> trainingTexts, int K) {
    return texts.stream().map(Classificator::classifyOne).toList();
  }

  public static Result classifyOne(Text text) {
    return new Result(Label.CANADA, Label.CANADA);
//    return trainingList.stream()
//        .collect(
//            Collectors.groupingBy(
//                Text::label,
//                Collectors.summingDouble(trainingText -> distance(text, trainingText))))
//        .entrySet()
//        .stream()
//        .max(Comparator.comparingDouble(Map.Entry::getValue))
//        .map(Map.Entry::getKey).orElse(null);
  }

//  public static List<Text> generateTrainingSet(List<Text> textList, int size) {
//    textList = new ArrayList<>(textList);
//    Collections.shuffle(textList);
//    return textList.subList(0, size);
//  }

  private float distance(Text firstText, Text secondText) {
    return DistanceMetricsCalculator.euclideanDistance(firstText.vector(), secondText.vector());
  }
}
