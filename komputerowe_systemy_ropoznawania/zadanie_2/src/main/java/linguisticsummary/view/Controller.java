package linguisticsummary.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import linguisticsummary.model.Qualifier;
import linguisticsummary.model.Summarizer;
import linguisticsummary.summary.SingleEntitySummarySecondForm;
import linguisticsummary.database.Initialization;
import linguisticsummary.database.MealDatabase;
import linguisticsummary.database.MealLabelDatabase;
import linguisticsummary.database.QuantifierDatabase;
import linguisticsummary.model.Meal;
import linguisticsummary.model.MealLabel;
import linguisticsummary.model.Quantifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static linguisticsummary.LinguisticSummary.allCombinations;

public class Controller {
    @FXML
    public Button randomSummariesButton;
    @FXML
    private TextArea summariesList;

//    @FXML
//    protected void generateSummaries() {
//        Initialization.initialize();
//
//        List<Meal> meals = MealDatabase.loadAll();
//        List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
//        List<MealLabel> allLabels = MealLabelDatabase.loadAll();
//
//        List<SingleEntitySummarySecondForm> allSummaries = new ArrayList<>();
//
//        for(List<MealLabel> labels : allCombinations(allLabels.stream().limit(10).toList(), 2)) {
//            for(Quantifier quantifier: allQuantifiers) {
//                allSummaries.add(new SingleEntitySummarySecondForm(quantifier, new Qualifier(List.of(labels.get(0))), new Summarizer(List.of(labels.get(1))), meals));
//            }
//        }
//        allSummaries.sort(Comparator.comparingDouble(value -> value.measures().getOptimalSummary()));
//        Collections.reverse(allSummaries);

//        var best = allSummaries.stream().filter(singleEntitySummarySecondForm -> !Double.isNaN(singleEntitySummarySecondForm.measures().getOptimalSummary())).limit(20).toList();
//        String text = best.stream().map(SingleEntitySummarySecondForm::toString).collect(Collectors.joining("\n"));
//        summariesList.setText(text);
//    }
}