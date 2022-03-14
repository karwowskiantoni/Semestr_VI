package classification.parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import classification.model.xml.ReutersList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ReutersParser {
  public static ReutersList parse(String data) throws JAXBException {
    Unmarshaller unmarshaller = JAXBContext.newInstance(ReutersList.class).createUnmarshaller();
    return (ReutersList) unmarshaller.unmarshal(stringToStream(formatXml(data)));
  }

  private static String formatXml(String xml) {
    return xml.replace("&", "").replace(System.lineSeparator(), "").replace("#", "");
  }

  private static InputStream stringToStream(String data) {
    return new ByteArrayInputStream(data.getBytes());
  }
}
