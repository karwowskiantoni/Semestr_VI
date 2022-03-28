package classificator.classification;

import classificator.model.internal.Label;
import classificator.model.internal.Result;
import classificator.model.internal.Text;

import java.util.*;

public class Classificator {

  public static List<Result> classifyAll(List<Text> texts, List<Text> trainingTexts, int K) {
    return texts.stream().map(text -> classifyOne(text, trainingTexts, K)).toList();
  }

  public static Result classifyOne(Text text, List<Text> trainingTexts, int K) {
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


  private float distance(Text firstText, Text secondText) {
    return DistanceMetricsCalculator.euclideanDistance(firstText.vector(), secondText.vector());
  }
}
