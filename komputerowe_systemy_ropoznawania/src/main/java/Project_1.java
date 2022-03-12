import model.ReutersList;
import parser.ReuterParser;

import javax.xml.bind.JAXBException;

public class Project_1 {
    public static void main(String[] args) throws JAXBException {
        ReutersList reutersList = ReuterParser.read("reut2-000.sgm");
        System.out.println(reutersList.getReutersList().size());
        System.out.println("gowno");
    }
}
