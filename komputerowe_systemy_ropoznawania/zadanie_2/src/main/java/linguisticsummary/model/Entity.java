package linguisticsummary.model;

import java.util.List;

public class Entity {
     private final List<Meal> meals;
     private final String name;

    public Entity(List<Meal> meals, String name) {
        this.meals = meals;
        this.name = name;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public String toString() {
        return name;
    }

    public int size() {
        return meals.size();
    }
}
