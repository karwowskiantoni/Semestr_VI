package linguisticsummary.database;

import linguisticsummary.model.Label;
import linguisticsummary.model.MealLabel;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MealLabelDatabase implements Database<Label> {
    public static List<MealLabel> loadAll() {
        File folder = new File("./src/main/resources/variables/");
        File[] listOfFiles = folder.listFiles();
        return Arrays.stream(listOfFiles).map(MealLabelDatabase::deserialize).toList();
    }

    public static void saveAll(List<MealLabel> labels) {
        labels.forEach(MealLabelDatabase::serialize);
    }

    private static void serialize(MealLabel label) {
        File outputFile = new File("./src/main/resources/variables/" + label.getLabel());
        createIfNotExists(outputFile);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            outputStream.writeObject(label);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MealLabel deserialize(File inputPath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputPath))) {
            return (MealLabel) inputStream.readObject();
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
