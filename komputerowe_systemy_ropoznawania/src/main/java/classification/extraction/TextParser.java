package classification.extraction;

import classification.extraction.xml.Reuters;
import classification.model.Label;
import classification.model.Text;

import java.util.List;

import static classification.model.Dictionaries.COUNTRIES;

public class TextParser {
  public static List<Text> parse(List<Reuters> reutersList) {
    return reutersList.stream()
        .filter(TextParser::filterFunction)
        .map(
            reuters ->
                new Text(
                    Label.valueOfLiteral(reuters.getPLACES().getD().get(0)),
                    FeatureExtractor.extract(reuters)))
        .toList();
  }

  private static boolean filterFunction(Reuters reuters) {
    return reuters.getPLACES() != null
        && reuters.getPLACES().getD() != null
        && reuters.getPLACES().getD().size() == 1
        && COUNTRIES.contains(reuters.getPLACES().getD().get(0))
        && reuters.getTEXT() != null
        && reuters.getTEXT().getTITLE() != null
        && reuters.getTEXT().getDATELINE() != null
        && reuters.getTEXT().getBODY() != null;
  }
}
