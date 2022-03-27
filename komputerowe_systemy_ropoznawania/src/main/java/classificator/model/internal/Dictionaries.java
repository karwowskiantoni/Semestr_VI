package classificator.model.internal;

import java.util.List;

public class Dictionaries {
    public static final List<String> COUNTRIES =
            List.of("west-germany", "usa", "france", "uk", "canada", "japan");

    public static final List<String> WEST_GERMANY_KEYWORDS =
            List.of("germany", "berlin", "NRD", "zurich", "marks", "mark", "deutsche", "frankfurt", "dusseldorf", "den", "west germany", "deutchsland", "bundestag");

    public static final List<String> USA_KEYWORDS =
            List.of("usa", "american", "nuclear", "dlr", "dlrs", "washington", "united", "states", "america", "boston", "u.s.", "philadelphia", "miami");

    public static final List<String> FRANCE_KEYWORDS =
            List.of("france", "paris", "seine", "bordeaux", "french", "franks", "frank", "jean", "renault", "louvre", "napoleon", "edouard");

    public static final List<String> UK_KEYWORDS =
            List.of("kingdom", "queen", "london", "u.k.", "manchester", "sir", "prime minister", "margaret", "thatcher", "greenwich", "britain", "ireland", "wales");

    public static final List<String> CANADA_KEYWORDS =
            List.of("canada", "canadian", "america", "cold", "ottawa", "toronto", "canadian", "vancouver", "polar", "dlr", "dlrs");

    public static final List<String> JAPAN_KEYWORDS =
            List.of("zhao-ziyang", "japan", "japanese", "yen", "yens", "east", "asia", "tokyo", "hiroshima", "nagasaki", "emperor");
}
