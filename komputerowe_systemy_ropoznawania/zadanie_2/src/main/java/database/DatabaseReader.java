package database;

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

class DatabaseReader {
    static List<String> read() {
        try {
            Path dbPath = Paths.get(Objects.requireNonNull(DatabaseReader.class.getClassLoader().getResource("database.csv")).toURI());

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

    static List<String> correctValues(List<String> data) {
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
