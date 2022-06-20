package linguisticsummary.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import linguisticsummary.database.Initialization;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.MealLabelDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.model.*;
import linguisticsummary.row.Row;
import linguisticsummary.summary.*;

import java.util.*;
import java.util.stream.Collectors;

import static linguisticsummary.view.Controller.SummaryType.SINGLE_ENTITY_SUMMARY_FIRST_FORM;

public class Controller {
    @FXML
    public Button generate;
    @FXML
    public Button type;

    enum SummaryType {
        SINGLE_ENTITY_SUMMARY_FIRST_FORM,
        SINGLE_ENTITY_SUMMARY_SECOND_FORM,
        MULTIPLE_ENTITY_SUMMARY_FIRST_FORM,
        MULTIPLE_ENTITY_SUMMARY_SECOND_FORM,
        MULTIPLE_ENTITY_SUMMARY_THIRD_FORM,
        MULTIPLE_ENTITY_SUMMARY_FOURTH_FORM,
    }

    @FXML
    public TableView<Row> table;
    @FXML
    public MenuButton quantifierMenu;
    @FXML
    public MenuButton summarizerMenu;
    @FXML
    public MenuButton qualifierMenu;

    @FXML
    public ToolBar quantifierToolBar;
    @FXML
    public ToolBar summarizerToolBar;
    @FXML
    public ToolBar qualifierToolBar;

    List<Quantifier> chosenQuantifiers = new ArrayList<>();
    List<MealLabel> chosenQualifierLabels = new ArrayList<>();
    List<MealLabel> chosenSummarizerLabels = new ArrayList<>();

    Entity allMeals = new Entity(MealDatabase.loadAll(), "meals");
    Entity antoniMeals;
    Entity michalMeals;

    List<Quantifier> allQuantifiers;
    List<MealLabel> allLabels;
    SummaryType summaryType = SINGLE_ENTITY_SUMMARY_FIRST_FORM;

    public void initialize() {
        Initialization.initialize();
        allQuantifiers = QuantifierDatabase.loadAll();
        allLabels = MealLabelDatabase.loadAll();
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("summary"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("T1"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("T2"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("T3"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("T4"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("T5"));
        table.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("T6"));
        table.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("T7"));
        table.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("T8"));
        table.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("T9"));
        table.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("T10"));
        table.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("T11"));
        table.getColumns().get(12).setCellValueFactory(new PropertyValueFactory<>("optimal"));
        quantifierToolBar.setOrientation(Orientation.VERTICAL);
        qualifierToolBar.setOrientation(Orientation.VERTICAL);
        summarizerToolBar.setOrientation(Orientation.VERTICAL);
        type.setText(summaryType.toString());
        antoniMeals = new Entity(allMeals
                .getMeals()
                .stream()
                .filter(meal -> meal.fatness() > 30.0)
                .toList(),
                "More fat dishes");
        michalMeals = new Entity(allMeals
                .getMeals()
                .stream()
                .filter(meal -> meal.fatness() < 30.0)
                .toList(),
                "Less fat dishes");
        update();
    }

    private void update() {
        List<Quantifier> filteredQuantifiers;
        if (summaryType == SINGLE_ENTITY_SUMMARY_FIRST_FORM) {
            filteredQuantifiers = allQuantifiers;
        } else {
            filteredQuantifiers = allQuantifiers.stream().filter(Quantifier::isRelative).toList();
        }

        quantifierMenu.getItems().clear();
        filteredQuantifiers.forEach(
                quantifier -> {
                    MenuItem item = new MenuItem(quantifier.toString());
                    EventHandler<ActionEvent> event =
                            e -> {
                                chosenQuantifiers.add(quantifier);
                                allQuantifiers.remove(quantifier);
                                update();
                            };
                    item.setOnAction(event);
                    quantifierMenu.getItems().add(item);
                });

        quantifierToolBar.getItems().clear();
        chosenQuantifiers.forEach(
                quantifier -> {
                    Button button = new Button(quantifier.toString());
                    EventHandler<ActionEvent> event =
                            e -> {
                                allQuantifiers.add(quantifier);
                                chosenQuantifiers.remove(quantifier);
                                update();
                            };
                    button.setOnAction(event);
                    quantifierToolBar.getItems().add(button);
                });

        summarizerMenu.getItems().clear();
        allLabels.forEach(
                label -> {
                    MenuItem item = new MenuItem(label.toString());
                    EventHandler<ActionEvent> event =
                            e -> {
                                chosenSummarizerLabels.add(label);
                                allLabels.remove(label);
                                update();
                            };
                    item.setOnAction(event);
                    summarizerMenu.getItems().add(item);
                });

        summarizerToolBar.getItems().clear();
        chosenSummarizerLabels.forEach(
                label -> {
                    Button button = new Button(label.toString());
                    EventHandler<ActionEvent> event =
                            e -> {
                                allLabels.add(label);
                                chosenSummarizerLabels.remove(label);
                                update();
                            };
                    button.setOnAction(event);
                    summarizerToolBar.getItems().add(button);
                });

        qualifierMenu.getItems().clear();
        allLabels.forEach(
                label -> {
                    MenuItem item = new MenuItem(label.toString());
                    EventHandler<ActionEvent> event =
                            e -> {
                                chosenQualifierLabels.add(label);
                                allLabels.remove(label);
                                update();
                            };
                    item.setOnAction(event);
                    qualifierMenu.getItems().add(item);
                });

        qualifierToolBar.getItems().clear();
        chosenQualifierLabels.forEach(
                label -> {
                    Button button = new Button(label.toString());
                    EventHandler<ActionEvent> event =
                            e -> {
                                allLabels.add(label);
                                chosenQualifierLabels.remove(label);
                                update();
                            };
                    button.setOnAction(event);
                    qualifierToolBar.getItems().add(button);
                });

        generate.setOnAction(e -> {
            table.getItems().clear();
            switch (summaryType) {
                case SINGLE_ENTITY_SUMMARY_FIRST_FORM -> singleEntityFirstForm();
                case SINGLE_ENTITY_SUMMARY_SECOND_FORM -> singleEntitySecondForm();
                case MULTIPLE_ENTITY_SUMMARY_FIRST_FORM -> multipleEntityFirstForm();
                case MULTIPLE_ENTITY_SUMMARY_SECOND_FORM -> multipleEntitySecondForm();
                case MULTIPLE_ENTITY_SUMMARY_THIRD_FORM -> multipleEntityThirdForm();
                case MULTIPLE_ENTITY_SUMMARY_FOURTH_FORM -> multipleEntityFourthForm();
            }
            update();
        });

        type.setOnAction(e -> {
            table.getItems().clear();
            switch (summaryType) {
                case SINGLE_ENTITY_SUMMARY_FIRST_FORM -> summaryType = SummaryType.SINGLE_ENTITY_SUMMARY_SECOND_FORM;
                case SINGLE_ENTITY_SUMMARY_SECOND_FORM -> summaryType = SummaryType.MULTIPLE_ENTITY_SUMMARY_FIRST_FORM;
                case MULTIPLE_ENTITY_SUMMARY_FIRST_FORM -> summaryType = SummaryType.MULTIPLE_ENTITY_SUMMARY_SECOND_FORM;
                case MULTIPLE_ENTITY_SUMMARY_SECOND_FORM -> summaryType = SummaryType.MULTIPLE_ENTITY_SUMMARY_THIRD_FORM;
                case MULTIPLE_ENTITY_SUMMARY_THIRD_FORM -> summaryType = SummaryType.MULTIPLE_ENTITY_SUMMARY_FOURTH_FORM;
                case MULTIPLE_ENTITY_SUMMARY_FOURTH_FORM -> summaryType = SINGLE_ENTITY_SUMMARY_FIRST_FORM;
            }
            type.setText(summaryType.toString());
            update();
        });
    }

    public void singleEntityFirstForm() {
        List<Summary> allSummaries = new ArrayList<>();
        for (List<MealLabel> summarizerLabels : allCombinations(chosenSummarizerLabels)) {
            for (Quantifier quantifier : chosenQuantifiers) {
                Summarizer summarizer = new Summarizer(summarizerLabels);
                allSummaries.add(new SingleEntitySummaryFirstForm(quantifier, summarizer, allMeals));
            }
        }
        allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
    }

    public void singleEntitySecondForm() {
        List<Summary> allSummaries = new ArrayList<>();
        for (List<MealLabel> summarizerLabels : allCombinations(chosenSummarizerLabels)) {
            for (List<MealLabel> qualifierLabels : allCombinations(chosenQualifierLabels)) {
                for (Quantifier quantifier : chosenQuantifiers) {
                    Summarizer summarizer = new Summarizer(summarizerLabels);
                    Qualifier qualifier = new Qualifier(qualifierLabels);
                    allSummaries.add(
                            new SingleEntitySummarySecondForm(quantifier, qualifier, summarizer, allMeals));
                }
            }
        }
        allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
    }

    public void multipleEntityFirstForm() {
        List<Summary> allSummaries = new ArrayList<>();
        for (List<MealLabel> summarizerLabels : allCombinations(chosenSummarizerLabels)) {
            for (Quantifier quantifier : chosenQuantifiers) {
                Summarizer summarizer = new Summarizer(summarizerLabels);
                allSummaries.add(
                        new MultipleEntitySummaryFirstForm(quantifier, summarizer, antoniMeals, michalMeals));
            }
        }
        allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
    }

    public void multipleEntitySecondForm() {
        List<Summary> allSummaries = new ArrayList<>();
        for (List<MealLabel> summarizerLabels : allCombinations(chosenSummarizerLabels)) {
            for (List<MealLabel> qualifierLabels : allCombinations(chosenQualifierLabels)) {
                for (Quantifier quantifier : chosenQuantifiers) {
                    Summarizer summarizer = new Summarizer(summarizerLabels);
                    Qualifier qualifier = new Qualifier(qualifierLabels);
                    allSummaries.add(
                            new MultipleEntitySummarySecondForm(
                                    quantifier, qualifier, summarizer, antoniMeals, michalMeals));
                }
            }
        }
        allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
    }

    public void multipleEntityThirdForm() {
        List<Summary> allSummaries = new ArrayList<>();
        for (List<MealLabel> summarizerLabels : allCombinations(chosenSummarizerLabels)) {
            for (List<MealLabel> qualifierLabels : allCombinations(chosenQualifierLabels)) {
                for (Quantifier quantifier : chosenQuantifiers) {
                    Summarizer summarizer = new Summarizer(summarizerLabels);
                    Qualifier qualifier = new Qualifier(qualifierLabels);
                    allSummaries.add(
                            new MultipleEntitySummaryThirdForm(
                                    quantifier, qualifier, summarizer, antoniMeals, michalMeals));
                }
            }
        }
        allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
    }

    public void multipleEntityFourthForm() {
        List<Summary> allSummaries = new ArrayList<>();
        for (List<MealLabel> summarizerLabels : allCombinations(chosenSummarizerLabels)) {
            Summarizer summarizer = new Summarizer(summarizerLabels);
            allSummaries.add(new MultipleEntitySummaryFourthForm(summarizer, antoniMeals, michalMeals));
        }
        allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
    }

    private static <T> Set<List<T>> allCombinations(List<T> objects) {
        Set<List<T>> output = new HashSet<>();
        for (int i = 1; i <= objects.size(); i++) {
            output.addAll(allCombinations(objects, i));
        }
        return output;
    }

    private static <T> Set<List<T>> allCombinations(List<T> objects, int k) {
        Set<List<T>> subarrays = new HashSet<>();
        recursiveSearch(objects, 0, k, subarrays, new ArrayList<>());
        return subarrays;
    }

    private static <T> void recursiveSearch(
            List<T> objects, int i, int k, Set<List<T>> subarrays, List<T> out) {
        if (objects.size() == 0 || k > objects.size()) {
            return;
        } else if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            return;
        }
        for (int j = i; j < objects.size(); j++) {
            out.add(objects.get(j));
            recursiveSearch(objects, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);
        }
    }
}
