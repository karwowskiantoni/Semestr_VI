package linguisticsummary.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private TableView<String> table;

    @FXML
    private TextArea summariesList;

    @FXML
    private void initialize(){
        String o = table.getColumns().get(0);
    }

    @FXML
    protected void generateSummaries() {

    }
}