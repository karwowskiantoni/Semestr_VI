package parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import model.ReutersList;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReuterParser {
  public static ReutersList read(String filename) throws JAXBException, IOException {
    Unmarshaller unmarshaller = JAXBContext.newInstance(ReutersList.class).createUnmarshaller();
    return (ReutersList) unmarshaller.unmarshal(stringToStream(formatXml(filenameToString(filename))));
  }

  private static String formatXml(String xml) {
    return xml
            .replaceAll("&", "")
            .replaceAll(System.lineSeparator(), "")
            .replaceAll("#", "");
  }

  private static InputStream stringToStream(String data) {
    return new ByteArrayInputStream(data.getBytes());
  }

  private static String filenameToString(String filename) throws IOException {
    return new String(
        ReuterParser.class.getClassLoader().getResourceAsStream(filename).readAllBytes());
  }
}
