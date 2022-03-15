package classification;

import jakarta.xml.bind.JAXBException;
import classification.model.internal.Vector;
import classification.model.xml.ReutersList;
import classification.parser.FileReader;
import classification.parser.ReutersParser;
import classification.parser.VectorParser;

import java.io.IOException;
import java.util.List;

public class KnnClassification {
  public static void main(String[] args) throws JAXBException, IOException {
    String data = FileReader.read("reut2-000.sgm");
    ReutersList reutersList = ReutersParser.parse(data);
    List<Vector> vectorList = VectorParser.parse(reutersList);
    System.out.println(vectorList.size());
    vectorList.forEach(System.out::println);
    }
}