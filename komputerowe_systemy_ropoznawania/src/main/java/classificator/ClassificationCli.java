package classificator;

import classificator.classification.Classificator;
import classificator.classification.QualityMeasuresCalculator;
import classificator.extraction.Extractor;
import classificator.model.internal.Result;
import classificator.model.internal.Text;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@Command(
    name = "classificator/classification",
    mixinStandardHelpOptions = true,
    version = "classification 1.0",
    description = "returns quality measures of classification algorithm")
public class ClassificationCli implements Callable<Integer> {

  @Option(
      names = {"-k", "--K"},
      description = "K, number of nearest neighbours considered in classification algorithm",
      showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
  private Integer K = 10;

  @Option(
      names = {"-ds", "--datasize"},
      description = "size of texts set from database used for classification (chosen randomly)",
      showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
  private Integer dsSize = 1000;

  @Option(
      names = {"-ts", "-testsize"},
      description = "size of texts training set (chosen randomly, shouldn't be bigger than number parameter)",
      showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
  private Integer tsSize = 200;

  @Option(
      names = {"-m", "--metric"},
      description = "distance metric used for classification, (chebyshev/euclidean/taxicab)",
      showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
  private String metricType = "chebyshev";

  public static List<Text> randomChoices(List<Text> textList, int size) {
    textList = new ArrayList<>(textList);
    Collections.shuffle(textList);
    return textList.subList(0, size);
  }

  @Override
  public Integer call() throws Exception {
    List<Text> texts = Extractor.extractAll();
    List<Text> textsToClassify = randomChoices(texts, dsSize);
    List<Text> trainingTexts = randomChoices(textsToClassify, tsSize);
    List<Result> results = Classificator.classifyAll(textsToClassify, trainingTexts, K);
    QualityMeasuresCalculator.printMetrics(results);
    return 0;
  }

  public static void main(String... args) {
    System.exit(new CommandLine(new ClassificationCli()).execute(args));
  }
}
