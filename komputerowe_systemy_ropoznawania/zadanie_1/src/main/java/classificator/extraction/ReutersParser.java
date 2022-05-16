package classificator.extraction;

import classificator.model.xml.Reuters;
import classificator.model.xml.ReutersList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class ReutersParser {
  public static List<Reuters> parse(String data) throws JAXBException {
    Unmarshaller unmarshaller = JAXBContext.newInstance(ReutersList.class).createUnmarshaller();
    return ((ReutersList) unmarshaller.unmarshal(stringToStream(formatXml(data)))).getReutersList();
  }

  private static String formatXml(String xml) {
    return xml.replace("&", "").replace(System.lineSeparator(), "").replace("#", "");
  }

  private static InputStream stringToStream(String data) {
    return new ByteArrayInputStream(data.getBytes());
  }
}
