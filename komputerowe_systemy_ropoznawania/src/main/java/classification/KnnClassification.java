package classification;

import classification.extraction.Extractor;
import classification.extraction.FileReader;
import classification.extraction.ReutersParser;
import classification.extraction.TextParser;
import classification.extraction.xml.Reuters;
import classification.model.Text;
import jakarta.xml.bind.JAXBException;

import java.util.List;

public class KnnClassification {
  public static void main(String[] args) {
    List<Text> textList = Extractor.extract("reut2-000.sgm");
    textList.forEach(System.out::println);
  }
}
