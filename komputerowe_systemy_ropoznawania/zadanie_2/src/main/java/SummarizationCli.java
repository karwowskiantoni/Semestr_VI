import database.Meal;
import database.MealDatabase;

import java.util.List;

class SummarizationCli {
    public static void main(String... args) {
        List<Meal> data = MealDatabase.data();
        System.out.println(data.get(0));
        System.out.println(data.get(1));
        System.out.println(data.get(2));
        System.out.println(data.get(3));
        System.out.println(data.get(4));
    }
}

