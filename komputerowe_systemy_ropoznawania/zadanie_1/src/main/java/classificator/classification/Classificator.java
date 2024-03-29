package classificator.classification;

import classificator.model.internal.Label;
import classificator.model.internal.Result;
import classificator.model.internal.Text;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Classificator {

  public static List<Result> classifyAll(List<Text> texts, List<Text> trainingTexts, int K, String method) {
    List<Result> results = new ArrayList<>();
    for (int i = 0; i < texts.size(); i++) {
      printPercentage(i, texts.size());
      results.add(classifyOne(texts.get(i), trainingTexts, K, method));
    }
    return results;
  }

  public static Result classifyOne(Text text, List<Text> trainingTexts, int K, String method) {
    Label predictedLabel =
        trainingTexts.stream()
            .sorted(Comparator.comparingDouble(value -> distance(text, value, method)))
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

  private static float distance(Text firstText, Text secondText, String method) {
//    return 1;
    if(method.equals("euclidean")) {
      return DistanceMetricsCalculator.euclideanDistance(firstText.vector(), secondText.vector());
    } else if(method.equals("chebyshev")) {
      return DistanceMetricsCalculator.chebyshevDistance(firstText.vector(), secondText.vector());
    } else {
      return DistanceMetricsCalculator.taxiCabDistance(firstText.vector(), secondText.vector());
    }
  }

  private static void printPercentage(int iterator, int size) {
    System.out.print("\b\b\b\b\b\b\b");
    System.out.printf("%.2f", iterator * 100.0 / size);
    System.out.print( " %");
    if(size <= iterator + 1) {
      System.out.print("\b\b\b\b\b\b\b");
    }
  }
}
