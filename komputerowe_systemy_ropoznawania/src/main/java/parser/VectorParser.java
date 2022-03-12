package parser;

import model.internal.Vector;
import model.xml.Reuters;
import model.xml.ReutersList;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static model.Dictionaries.*;

public class VectorParser {

  public static List<Vector> parse(ReutersList reutersList) {
    return reutersList.getReutersList().stream()
        .filter(VectorParser::filterFunction)
        .map(
            reuters ->
                new Vector(
                    firstWordInDateLine(reuters),
                    numberOfWordsInDictionary(WEST_GERMANY_KEYWORDS, reuters.getTEXT().getBODY()),
                    numberOfWordsInDictionary(USA_KEYWORDS, reuters.getTEXT().getBODY()),
                    numberOfWordsInDictionary(FRANCE_KEYWORDS, reuters.getTEXT().getBODY()),
                    numberOfWordsInDictionary(UK_KEYWORDS, reuters.getTEXT().getBODY()),
                    numberOfWordsInDictionary(CANADA_KEYWORDS, reuters.getTEXT().getBODY()),
                    numberOfWordsInDictionary(JAPAN_KEYWORDS, reuters.getTEXT().getBODY())))
        .toList();
  }

  private static String firstWordInDateLine(Reuters reuters) {
    return reuters.getTEXT().getDATELINE().split(" ")[0];
  }

  private static int numberOfWordsInDictionary(List<String> dictionary, String text) {
    int counter = 0;
    for (String textWord : text.split(" ")) {
      for (String dictionaryWord : dictionary) {
        if (textWord.equalsIgnoreCase(dictionaryWord)) counter++;
      }
    }
    return counter;
  }

  private static int mostCommonWordFromDictionary(List<String> dictionary, String text) {
    int counter = 0;
    for (String textWord : text.split(" ")) {
      for (String dictionaryWord : dictionary) {
        if (textWord.equalsIgnoreCase(dictionaryWord)) counter++;
      }
    }
    return counter;
  }

  private static String firstCountryInTitle() {
    return "";
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