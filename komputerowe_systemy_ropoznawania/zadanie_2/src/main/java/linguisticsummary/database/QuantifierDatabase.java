package linguisticsummary.database;

import linguisticsummary.model.Quantifier;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class QuantifierDatabase {
    public static List<Quantifier> loadAll() {
        File folder = new File("./src/main/resources/quantifiers/");
        File[] listOfFiles = folder.listFiles();
        return Arrays.stream(listOfFiles).map(QuantifierDatabase::deserialize).toList();
    }

    public static void saveAll(List<Quantifier> quantifiers) {
        quantifiers.forEach(QuantifierDatabase::serialize);
    }

    private static void serialize(Quantifier quantifier) {
        File outputFile = new File("./src/main/resources/quantifiers/" + quantifier.getLabel());
        createIfNotExists(outputFile);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            outputStream.writeObject(quantifier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Quantifier deserialize(File inputPath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputPath))) {
            return (Quantifier) inputStream.readObject();
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
