package classificator;

import classificator.classification.Classificator;
import classificator.classification.QualityMeasuresCalculator;
import classificator.extraction.Extractor;
import classificator.model.internal.Result;
import classificator.model.internal.Text;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

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
  private Integer K = 1;

  @Option(
      names = {"-p", "--pivot"},
      description =
          "pivot in which dataset is splitted into test set and training set, (value between 0 and 100)",
      showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
  private Integer pivot = 20;

  @Option(
      names = {"-m", "--metric"},
      description = "distance metric used for classification, (chebyshev/euclidean/taxicab)",
      showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
  private String metricType = "euclidean";

  @Override
  public Integer call() throws Exception {
    List<Text> dataSet = Extractor.extractAll();

    int pivotPosition = (int) (dataSet.size() * (pivot * 1.0 / 100));
    List<Text> testSet = dataSet.subList(0, pivotPosition);
    List<Text> trainingSet = dataSet.subList(pivotPosition, dataSet.size());

    List<Result> results = Classificator.classifyAll(testSet, trainingSet, K, metricType);
    QualityMeasuresCalculator.printMetrics(results);

    return 0;
  }

  public static void main(String... args) {
    System.exit(new CommandLine(new ClassificationCli()).execute(args));
  }
}
