package parser;

import model.ReutersList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReuterParser {
    public static ReutersList read(String filename) throws JAXBException {
            File file = new File(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(ReutersList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (ReutersList) jaxbUnmarshaller.unmarshal(new File(""));
    }
}
