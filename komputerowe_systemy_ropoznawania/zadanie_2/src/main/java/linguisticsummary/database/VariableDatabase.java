package linguisticsummary.database;

import linguisticsummary.model.Label;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class VariableDatabase implements Database<Label> {
    public static List<Label> loadAll() {
        File folder = new File("./src/main/resources/variables/");
        File[] listOfFiles = folder.listFiles();
        return Arrays.stream(listOfFiles).map(VariableDatabase::deserialize).toList();
    }

    public static void saveAll(List<Label> labels) {
        labels.forEach(VariableDatabase::serialize);
    }

    private static void serialize(Label label) {
        File outputFile = new File("./src/main/resources/variables/" + label.getLabel());
        createIfNotExists(outputFile);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            outputStream.writeObject(label);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Label deserialize(File inputPath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputPath))) {
            return (Label) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    private static void createIfNotExists(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
