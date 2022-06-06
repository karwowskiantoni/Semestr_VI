package linguisticsummary.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import linguisticsummary.database.Initialization;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.MealLabelDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.model.*;
import linguisticsummary.row.Row;
import linguisticsummary.row.SingleEntityRowSecondForm;
import linguisticsummary.summary.MultipleEntitySummaryFirstForm;
import linguisticsummary.summary.SingleEntitySummaryFirstForm;
import linguisticsummary.summary.SingleEntitySummarySecondForm;
import linguisticsummary.summary.Summary;

import java.util.*;

public class Controller {
    @FXML
    private TableView<Row> table;

    @FXML
    public void initialize(){
        Initialization.initialize();
        Entity all = new Entity(MealDatabase.loadAll(), "meals");
        Entity left = new Entity(all.getMeals().subList(0, 5000), "Antoni's dishes");
        Entity right = new Entity(all.getMeals().subList(5000, all.size()), "Michal's dishes");
        List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
        List<MealLabel> allLabels = MealLabelDatabase.loadAll();
        List<Summary> allSummaries = new ArrayList<>();
        for(List<MealLabel> labels : allCombinations(allLabels.stream().limit(10).toList(), 2)) {
            for(Quantifier quantifier: allQuantifiers) {
//                 single entity first form
//                Summarizer summarizer = new Summarizer(labels);
//                allSummaries.add(new SingleEntitySummaryFirstForm(quantifier, summarizer, all));
//                 single entity second form
                Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
                allSummaries.add(new SingleEntitySummarySecondForm(quantifier, qualifier, summarizer, all));
//                 multiple entity first form
//                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//                allSummaries.add(new MultipleEntitySummaryFirstForm(quantifier, summarizer, left, right));
                // multiple entity second form
//                Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
//                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//                allSummaries.add(new MultipleEntitySummarySecondForm(quantifier, qualifier, summarizer, firstHalf, secondHalf));
                // multiple entity third form
//                Qualifier qualifier = new Qualifier(List.of(labels.get(0)));
//                Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//                allSummaries.add(new MultipleEntitySummaryThirdForm(quantifier, qualifier, summarizer, left, right));
            }
            // multiple entity fourth form
//            Summarizer summarizer = new Summarizer(List.of(labels.get(1)));
//            allSummaries.add(new MultipleEntitySummaryFourthForm(summarizer, firstHalf, secondHalf));
        }
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
        allSummaries.stream().map(Summary::toRow).forEach(row -> table.getItems().add(row));
    }

    private static <T> List<T> random(List<T> list, int n) {
        List<T> newList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            newList.add(list.get(random.nextInt(list.size())));
        }
        return newList;
    }

    private static <T> Set<List<T>> allCombinations(List<T> objects, int k) {
        Set<List<T>> subarrays = new HashSet<>();
        recursiveSearch(objects, 0, k, subarrays, new ArrayList<>());
        return subarrays;
    }

    private static <T> void recursiveSearch(List<T> objects, int i, int k, Set<List<T>> subarrays, List<T> out) {
        if (objects.size() == 0 || k > objects.size()) {
            return;
        } else if (k == 0) {
            subarrays.add(new ArrayList<>(out));
            return;
        } for (int j = i; j < objects.size(); j++) {
            out.add(objects.get(j));
            recursiveSearch(objects, j + 1, k - 1, subarrays, out);
            out.remove(out.size() - 1);
        }
    }
}