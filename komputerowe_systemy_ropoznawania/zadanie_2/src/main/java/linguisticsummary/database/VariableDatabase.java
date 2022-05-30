package linguisticsummary.database;

import linguisticsummary.model.Variable;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class VariableDatabase {
    public static List<Variable> loadAll() {
        File folder = new File("./src/main/resources/variables/");
        File[] listOfFiles = folder.listFiles();
        return Arrays.stream(listOfFiles).map(VariableDatabase::deserialize).toList();
    }

    public static void saveAll(List<Variable> variables) {
        variables.forEach(VariableDatabase::serialize);
    }

    private static void serialize(Variable variable) {
        File outputFile = new File("./src/main/resources/variables/" + variable.getLabel());
        createIfNotExists(outputFile);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            outputStream.writeObject(variable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Variable deserialize(File inputPath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputPath))) {
            return (Variable) inputStream.readObject();
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
