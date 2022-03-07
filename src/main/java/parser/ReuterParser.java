package parser;

import model.Reuters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ReuterParser {
    public static List<Reuters> read(String filename) {
        try {
            File file = new File(filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(Reuters.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Reuters e = (Reuters) jaxbUnmarshaller.unmarshal(file);

        } catch (
                JAXBException e) {
            e.printStackTrace();
        }
    }

    private static String toXmlFormat() {

    }
}
