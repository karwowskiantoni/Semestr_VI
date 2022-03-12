import jakarta.xml.bind.JAXBException;
import model.ReutersList;
import parser.ReuterParser;

import java.io.IOException;

public class KnnClassification {
  public static void main(String[] args) throws JAXBException, IOException {
    ReutersList reutersList = ReuterParser.read("reut2-000.sgm");
    reutersList
        .getReutersList()
        .forEach(reuters -> System.out.println(reuters.getPLACES().getD()));
  }
}
