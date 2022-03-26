package classification;

import classification.algorithm.Classificator;
import classification.extraction.Extractor;
import classification.model.Text;

import java.util.List;

public class KnnClassification {
  public static void main(String[] args) {
    List<Text> textList = Extractor.extract("reut2-000.sgm");
    Classificator classificator = new Classificator(Classificator.generateTrainingSet(textList, 100), 10);
    classificator.classifyAll(textList);
  }
}
