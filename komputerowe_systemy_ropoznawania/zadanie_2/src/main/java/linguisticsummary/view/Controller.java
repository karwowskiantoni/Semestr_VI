package linguisticsummary.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import linguisticsummary.database.Initialization;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.MealLabelDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.model.*;
import linguisticsummary.row.Row;
import linguisticsummary.summary.*;

import java.util.*;

public class Controller {
  @FXML private TableView<Row> table;

  @FXML private MenuButton summarizerMenu;

  List<Quantifier> chosenQuantifiers = new ArrayList<>();
  List<MealLabel> chosenLabels = new ArrayList<>();

  Entity all = new Entity(MealDatabase.loadAll(), "meals");
  Entity left = new Entity(all.getMeals().subList(0, 5000), "Antoni's dishes");
  Entity right = new Entity(all.getMeals().subList(5000, all.size()), "Michal's dishes");

  public void initialize() {
    Initialization.initialize();
    List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
    List<MealLabel> allLabels = MealLabelDatabase.loadAll();
    List<Summary> allSummaries = new ArrayList<>();
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

    allLabels.forEach(
            mealLabel -> {
              MenuItem item = new MenuItem(mealLabel.toString());
              EventHandler<ActionEvent> event = e -> System.out.println(mealLabel);
              item.setOnAction(event);
              summarizerMenu.getItems().add(item);
            });
  }

  public void singleEntityFirstForm() {
    List<Summary> allSummaries = new ArrayList<>();
    for (List<MealLabel> labels : allCombinations(chosenLabels, 2)) {
      for (Quantifier quantifier : chosenQuantifiers) {
        Summarizer summarizer = new Summarizer(labels);
        allSummaries.add(new SingleEntitySummaryFirstForm(quantifier, summarizer, all));
      }
    }
    allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
  }

  public void singleEntitySecondForm() {
    List<Summary> allSummaries = new ArrayList<>();
    for (List<MealLabel> labels : allCombinations(chosenLabels, 2)) {
      for (Quantifier quantifier : chosenQuantifiers) {
        Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
        Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
        allSummaries.add(new SingleEntitySummarySecondForm(quantifier, qualifier, summarizer, all));
      }
    }
    allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
  }

  public void multipleEntityFirstForm() {
    List<Summary> allSummaries = new ArrayList<>();
    for (List<MealLabel> labels : allCombinations(chosenLabels, 2)) {
      for (Quantifier quantifier : chosenQuantifiers) {
        Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
        allSummaries.add(new MultipleEntitySummaryFirstForm(quantifier, summarizer, left, right));
      }
    }
    allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
  }

  public void multipleEntitySecondForm() {
    List<Summary> allSummaries = new ArrayList<>();
    for (List<MealLabel> labels : allCombinations(chosenLabels, 2)) {
      for (Quantifier quantifier : chosenQuantifiers) {
        Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
        Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
        allSummaries.add(
            new MultipleEntitySummarySecondForm(quantifier, qualifier, summarizer, left, right));
      }
    }
    allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
  }

  public void multipleEntityThirdForm() {
    List<Summary> allSummaries = new ArrayList<>();
    for (List<MealLabel> labels : allCombinations(chosenLabels, 2)) {
      for (Quantifier quantifier : chosenQuantifiers) {
        Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
        Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
        allSummaries.add(new MultipleEntitySummaryThirdForm(quantifier, qualifier, summarizer, left, right));
      }
    }
    allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
  }

  public void multipleEntityFourthForm() {
    List<Summary> allSummaries = new ArrayList<>();
    for (List<MealLabel> labels : allCombinations(chosenLabels, 2)) {
                  Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
                  allSummaries.add(new MultipleEntitySummaryFourthForm(summarizer, left, right));
    }
    allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
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
