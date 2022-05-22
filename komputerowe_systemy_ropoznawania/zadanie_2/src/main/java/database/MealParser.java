package database;

import java.util.List;
import java.util.stream.Collectors;

class MealParser {
    static List<Meal> parse() {
        return DatabaseReader.read().stream().map(record -> {
            String[] attributesValues = record.split(";");
            return new Meal(
                    attributesValues[1],
                    Double.parseDouble(attributesValues[2].replace(",", ".")),
                    Double.parseDouble(attributesValues[3].replace(",", ".")),
                    Double.parseDouble(attributesValues[4].replace(",", ".")),
                    Double.parseDouble(attributesValues[5].replace(",", ".")),
                    Double.parseDouble(attributesValues[6].replace(",", ".")),
                    Double.parseDouble(attributesValues[8].replace(",", ".")),
                    Double.parseDouble(attributesValues[10].replace(",", ".")),
                    Double.parseDouble(attributesValues[11].replace(",", ".")),
                    Double.parseDouble(attributesValues[12].replace(",", ".")),
                    Double.parseDouble(attributesValues[13].replace(",", ".")));
        }).collect(Collectors.toList());
    }
}
