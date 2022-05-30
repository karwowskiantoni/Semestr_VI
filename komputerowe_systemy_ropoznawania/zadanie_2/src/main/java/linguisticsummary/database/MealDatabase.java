package linguisticsummary.database;

import linguisticsummary.model.Meal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MealDatabase {
    public static List<Meal> loadAll() {
        return readCsv().stream().map(record -> {
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
        }).collect(toList());
    }

    private static List<String> readCsv() {
        try {
            Path dbPath = Paths.get(Objects.requireNonNull(MealDatabase.class.getClassLoader().getResource("database.csv")).toURI());
            Stream<String> fileLines = Files.lines(dbPath);
            List<String> withHeadings = fileLines.toList();
            fileLines.close();
            return correctValues(withHeadings.subList(2, withHeadings.size() - 1));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }

    private static List<String> correctValues(List<String> data) {
        return data.stream().map(record -> {
            String[] attributes = record.split(";");
            return Arrays.stream(attributes).map(attribute -> {
                if (attribute.isEmpty() || attribute.contains("#DZIEL/0!")) {
                    return "0";
                }
                return attribute;
            }).collect(joining(";"));
        }).collect(toList());
    }
}
