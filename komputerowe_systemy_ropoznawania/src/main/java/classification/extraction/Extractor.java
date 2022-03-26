package classification.extraction;

import classification.model.Text;
import jakarta.xml.bind.JAXBException;

import java.util.ArrayList;
import java.util.List;

public class Extractor {
    public static List<Text> extract(String filename) {
        try {
            return TextParser.parse(ReutersParser.parse(FileReader.read(filename)));
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

//    public static List<Text> extractAll() {
//
//    }
}
