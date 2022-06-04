package linguisticsummary.database;

import linguisticsummary.model.LabelFunction;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class VariableDatabase implements Database<LabelFunction> {
    public static List<LabelFunction> loadAll() {
        File folder = new File("./src/main/resources/variables/");
        File[] listOfFiles = folder.listFiles();
        return Arrays.stream(listOfFiles).map(VariableDatabase::deserialize).toList();
    }

    public static void saveAll(List<LabelFunction> labelFunctions) {
        labelFunctions.forEach(VariableDatabase::serialize);
    }

    private static void serialize(LabelFunction labelFunction) {
        File outputFile = new File("./src/main/resources/variables/" + labelFunction.getLabel());
        createIfNotExists(outputFile);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            outputStream.writeObject(labelFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static LabelFunction deserialize(File inputPath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputPath))) {
            return (LabelFunction) inputStream.readObject();
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
