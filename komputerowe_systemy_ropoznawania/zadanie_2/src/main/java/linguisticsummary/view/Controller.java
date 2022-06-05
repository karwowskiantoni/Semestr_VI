package linguisticsummary.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import linguisticsummary.calculation.Qualifier;
import linguisticsummary.calculation.Summarizer;
import linguisticsummary.calculation.Summary;
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

    @FXML
    protected void generateSummaries() {
        Initialization.initialize();

        List<Meal> meals = MealDatabase.loadAll();
        List<Quantifier> allQuantifiers = QuantifierDatabase.loadAll();
        List<MealLabel> allLabels = MealLabelDatabase.loadAll();

        List<Summary> allSummaries = new ArrayList<>();

        for(List<MealLabel> labels : allCombinations(allLabels.stream().limit(10).toList(), 2)) {
            for(Quantifier quantifier: allQuantifiers) {
                allSummaries.add(new Summary(quantifier, new Qualifier(List.of(labels.get(0))), new Summarizer(List.of(labels.get(1))), meals));
            }
        }
        allSummaries.sort(Comparator.comparingDouble(value -> value.measures().getOptimalSummary()));
        Collections.reverse(allSummaries);

        var best = allSummaries.stream().filter(summary -> !Double.isNaN(summary.measures().getOptimalSummary())).limit(20).toList();
        String text = best.stream().map(Summary::toString).collect(Collectors.joining("\n"));
        summariesList.setText(text);
    }
}