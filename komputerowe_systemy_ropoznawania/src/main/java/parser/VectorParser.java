package parser;

import model.internal.Vector;
import model.xml.Reuters;
import model.xml.ReutersList;

import java.util.List;

public class VectorParser {
  private static final List<String> AVAILABLE_COUNTRIES =
      List.of("west-germany", "usa", "france", "uk", "canada", "japan");

  public static List<Vector> parse(ReutersList reutersList) {
    return reutersList.getReutersList().stream()
        .filter(VectorParser::filterFunction)
        .map(reuters -> new Vector(
                  firstWordInDateLine(reuters)
                ))
        .toList();
  }

  private static String firstWordInDateLine(Reuters reuters) {
    return reuters.getTEXT().getDATELINE().split(" ")[0];
  }

  private static boolean filterFunction(Reuters reuters) {
    return reuters.getPLACES() != null
        && reuters.getPLACES().getD() != null
        && reuters.getPLACES().getD().size() == 1
        && AVAILABLE_COUNTRIES.contains(reuters.getPLACES().getD().get(0))
        && reuters.getTEXT() != null
        && reuters.getTEXT().getTITLE() != null
        && reuters.getTEXT().getDATELINE() != null
        && reuters.getTEXT().getBODY() != null;
  }
}
