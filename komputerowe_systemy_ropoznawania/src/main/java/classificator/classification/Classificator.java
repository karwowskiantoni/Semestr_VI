package classificator.classification;

import classificator.model.internal.Label;
import classificator.model.internal.Result;
import classificator.model.internal.Text;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Classificator {

  public static List<Result> classifyAll(List<Text> texts, List<Text> trainingTexts, int K) {
    return texts.stream().map(text -> classifyOne(text, trainingTexts, K)).toList();
  }

  public static Result classifyOne(Text text, List<Text> trainingTexts, int K) {
//    Collections.reverse();
    Label predictedLabel =
        trainingTexts.stream()
            .sorted(Comparator.comparingDouble(value -> distance(text, value)))
            .limit(K)
            .map(Text::label)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparingDouble(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse(null);
    return new Result(text.label(), predictedLabel);
  }

  private static float distance(Text firstText, Text secondText) {
    return DistanceMetricsCalculator.euclideanDistance(firstText.vector(), secondText.vector());
  }
}
