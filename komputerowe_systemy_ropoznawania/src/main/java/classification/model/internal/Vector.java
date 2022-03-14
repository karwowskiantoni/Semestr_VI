package classification.model.internal;

public record Vector(
        String firstWordInDateline,
        String firstCountryInTitle,
        String mostCommonWord,
        int firstDate,
        int lastDate,
        int westGermanyWordCount,
        int usaWordCount,
        int franceWordCount,
        int ukWordCount,
        int canadaWordCount,
        int japanWordCount
        ) {
}
