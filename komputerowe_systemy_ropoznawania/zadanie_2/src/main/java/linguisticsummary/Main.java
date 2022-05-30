package linguisticsummary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        stage.setTitle("Linguistic summaries of database");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
