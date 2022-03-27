package classificator;

import classificator.classification.Classificator;
import classificator.classification.QualityMeasuresCalculator;
import classificator.extraction.Extractor;
import classificator.model.internal.Result;
import classificator.model.internal.Text;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.List;
import java.util.concurrent.Callable;

@Command(
    name = "classificator/classification",
    mixinStandardHelpOptions = true,
    version = "classification 1.0",
    description = "returns quality measures of classification algorithm")
public class ClassificationCli implements Callable<Integer> {

  //  @Parameters(index = "0", description = "The file whose checksum to calculate.")
  //  private File file;

  //  @Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
  //  private String algorithm = "SHA-256";

  @Override
  public Integer call() throws Exception {
    List<Text> texts = Extractor.extractAll();
    List<Result> results = Classificator.classifyAll(texts, texts, 100);
    QualityMeasuresCalculator.printStatistics(results);
    return 0;
  }

  public static void main(String... args) {
    System.exit(new CommandLine(new ClassificationCli()).execute(args));
  }
}
