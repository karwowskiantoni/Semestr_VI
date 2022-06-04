package linguisticsummary.model;

import java.util.List;
import java.util.function.Function;

public class MealLabel extends Label {
    private final Function<Meal, Double> getAttribute;
    public MealLabel(String label, FunctionType type, List<Double> params, List<Double> domain, Function<Meal, Double> getAttribute) {
        super(label, type, params, domain);
        this.getAttribute = getAttribute;
    }

    public double membership(Meal meal) {
        return super.membership(getAttribute.apply(meal));
    }
}
