package classification.extraction;

import classification.model.Text;
import jakarta.xml.bind.JAXBException;

import java.util.ArrayList;
import java.util.List;

public class Extractor {

  private Extractor() {}

  public static List<Text> extract(String filename) {
    try {
      return TextParser.parse(ReutersParser.parse(FileReader.read(filename)));
    } catch (JAXBException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public static List<Text> extractAll() {
    return FILENAMES.stream().flatMap(s -> extract(s).stream()).toList();
  }

  public static final List<String> FILENAMES =
      List.of(
          "reut2-000.sgm",
          "reut2-001.sgm",
          "reut2-002.sgm",
          "reut2-003.sgm",
          "reut2-004.sgm",
          "reut2-005.sgm",
          "reut2-006.sgm",
          "reut2-007.sgm",
          "reut2-008.sgm",
          "reut2-009.sgm",
          "reut2-010.sgm",
          "reut2-011.sgm",
          "reut2-012.sgm",
          "reut2-013.sgm",
          "reut2-014.sgm",
          "reut2-015.sgm",
          "reut2-016.sgm",
          "reut2-017.sgm",
          "reut2-018.sgm",
          "reut2-019.sgm",
          "reut2-020.sgm",
          "reut2-021.sgm");
}
