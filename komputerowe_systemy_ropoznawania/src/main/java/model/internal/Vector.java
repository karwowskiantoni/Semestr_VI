package model.internal;

public record Vector(
        String firstWordInDateline,
        int westGermanyWordCount,
        int usaWordCount,
        int franceWordCount,
        int ukWordCount,
        int canadaWordCount,
        int japanWordCount
        ) {
}
