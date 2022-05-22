package database;

import java.util.List;

public class MealDatabase {
    public static List<Meal> data(){
        return MealParser.parse();
    }
}
