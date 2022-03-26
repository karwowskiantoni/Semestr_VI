package classification.extraction;

import classification.extraction.xml.Reuters;
import classification.model.Vector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static classification.model.Dictionaries.*;

public class FeatureExtractor {

  public static Vector extract(Reuters reuters) {
    return new Vector(
        firstWordInDateLine(reuters),
        wordsInDictionary(COUNTRIES, reuters.getTEXT().getTITLE()).stream().findFirst().orElse(""),
        mostCommonWord(reuters.getTEXT().getBODY()),
        getAllValidDates(reuters.getTEXT().getBODY()).stream()
            .mapToInt(value -> value)
            .min()
            .orElse(0),
        getAllValidDates(reuters.getTEXT().getBODY()).stream()
            .mapToInt(value -> value)
            .max()
            .orElse(0),
        numberOfWordsInDictionary(WEST_GERMANY_KEYWORDS, reuters.getTEXT().getBODY()),
        numberOfWordsInDictionary(USA_KEYWORDS, reuters.getTEXT().getBODY()),
        numberOfWordsInDictionary(FRANCE_KEYWORDS, reuters.getTEXT().getBODY()),
        numberOfWordsInDictionary(UK_KEYWORDS, reuters.getTEXT().getBODY()),
        numberOfWordsInDictionary(CANADA_KEYWORDS, reuters.getTEXT().getBODY()),
        numberOfWordsInDictionary(JAPAN_KEYWORDS, reuters.getTEXT().getBODY()));
  }

  private static String firstWordInDateLine(Reuters reuters) {
    return reuters.getTEXT().getDATELINE().split(" ")[0];
  }

  private static int numberOfWordsInDictionary(List<String> dictionary, String text) {
    return wordsInDictionary(dictionary, text).size();
  }

  private static List<String> wordsInDictionary(List<String> dictionary, String text) {
    List<String> words = new ArrayList<>();
    for (String textWord : text.split(" ")) {
      for (String dictionaryWord : dictionary) {
        if (textWord.equalsIgnoreCase(dictionaryWord)) words.add(textWord);
      }
    }
    return words;
  }

  private static String mostCommonWord(String text) {

    return Stream.of(text.split(" "))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .stream()
        .filter(stringLongEntry -> !stringLongEntry.getKey().equals(""))
        .max(Comparator.comparingLong(Map.Entry::getValue))
        .map(Map.Entry::getKey)
        .orElse("");
  }

  private static List<Integer> getAllValidDates(String text) {
    List<Integer> dates = new ArrayList<>();
    Matcher m = Pattern.compile("(?=(" + "[1][9][6-9][0-9]" + "))").matcher(text);
    while (m.find()) {
      dates.add(Integer.parseInt(m.group(1)));
    }
    return dates;
  }
}
