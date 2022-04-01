package classificator.classification;

import classificator.model.internal.Vector;

import java.util.List;

public class DistanceMetricsCalculator {
    public static int euclideanDistance(Vector firstVector, Vector secondVector) {
        return (int) (Math.pow(SimilarityMeasuresCalculator.wordSimilarity(firstVector.firstWordInDateline(), secondVector.firstWordInDateline()), 2)
                + Math.pow(SimilarityMeasuresCalculator.wordSimilarity(firstVector.firstCountryInTitle(), secondVector.firstCountryInTitle()), 2)
                + Math.pow(SimilarityMeasuresCalculator.wordSimilarity(firstVector.mostCommonWord(), secondVector.mostCommonWord()), 2)
                + Math.pow(firstVector.firstDate() - secondVector.firstDate(), 2)
                + Math.pow(firstVector.lastDate() - secondVector.lastDate(), 2)
                + Math.pow(firstVector.westGermanyWordCount() - secondVector.westGermanyWordCount(), 2)
                + Math.pow(firstVector.usaWordCount() - secondVector.usaWordCount(), 2)
                + Math.pow(firstVector.franceWordCount() - secondVector.franceWordCount(), 2)
                + Math.pow(firstVector.ukWordCount() - secondVector.ukWordCount(), 2)
                + Math.pow(firstVector.canadaWordCount() - secondVector.canadaWordCount(), 2)
                + Math.pow(firstVector.japanWordCount() - secondVector.japanWordCount(), 2));
    }

    public static int taxiCabDistance(Vector firstVector, Vector secondVector) {
        return SimilarityMeasuresCalculator.wordSimilarity(firstVector.firstWordInDateline(), secondVector.firstWordInDateline())
                + SimilarityMeasuresCalculator.wordSimilarity(firstVector.firstCountryInTitle(), secondVector.firstCountryInTitle()) +
                SimilarityMeasuresCalculator.wordSimilarity(firstVector.mostCommonWord(), secondVector.mostCommonWord()) +
                Math.abs(firstVector.firstDate() - secondVector.firstDate()) +
                Math.abs(firstVector.lastDate() - secondVector.lastDate()) +
                Math.abs(firstVector.westGermanyWordCount() - secondVector.westGermanyWordCount()) +
                Math.abs(firstVector.usaWordCount() - secondVector.usaWordCount()) +
                Math.abs(firstVector.franceWordCount() - secondVector.franceWordCount()) +
                Math.abs(firstVector.ukWordCount() - secondVector.ukWordCount()) +
                Math.abs(firstVector.canadaWordCount() - secondVector.canadaWordCount()) +
                Math.abs(firstVector.japanWordCount() - secondVector.japanWordCount());
    }

    public static int chebyshevDistance(Vector firstVector, Vector secondVector) {
        List<Integer> distances = List.of(
                SimilarityMeasuresCalculator.wordSimilarity(firstVector.firstWordInDateline(), secondVector.firstWordInDateline()),
                SimilarityMeasuresCalculator.wordSimilarity(firstVector.firstCountryInTitle(), secondVector.firstCountryInTitle()),
                SimilarityMeasuresCalculator.wordSimilarity(firstVector.mostCommonWord(), secondVector.mostCommonWord()),
                Math.abs(firstVector.firstDate() - secondVector.firstDate()),
                Math.abs(firstVector.lastDate() - secondVector.lastDate()),
                Math.abs(firstVector.westGermanyWordCount() - secondVector.westGermanyWordCount()),
                Math.abs(firstVector.usaWordCount() - secondVector.usaWordCount()),
                Math.abs(firstVector.franceWordCount() - secondVector.franceWordCount()),
                Math.abs(firstVector.ukWordCount() - secondVector.ukWordCount()),
                Math.abs(firstVector.canadaWordCount() - secondVector.canadaWordCount()),
                Math.abs(firstVector.japanWordCount() - secondVector.japanWordCount())
        );
        return distances.stream().mapToInt(Integer::intValue).max().getAsInt();
    }
}
