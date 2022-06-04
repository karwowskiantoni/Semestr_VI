package linguisticsummary.model;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class MealLabel extends Label implements Serializable {
    private final MealFunction getAttribute;
    public MealLabel(String label, FunctionType type, List<Double> params, List<Double> domain, MealFunction getAttribute) {
        super(label, type, params, domain);
        this.getAttribute = getAttribute;
    }

    public double membership(Meal meal) {
        return super.membership(getAttribute.apply(meal));
    }

    public interface MealFunction extends Function<Meal, Double>, Serializable{}
}
