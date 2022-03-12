package parser;

import java.io.IOException;
import java.util.Objects;

public class FileReader {
  public static String read(String filename) {
    try {
      return new String(
          Objects.requireNonNull(ReutersParser.class.getClassLoader().getResourceAsStream(filename)).readAllBytes());
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }
}
