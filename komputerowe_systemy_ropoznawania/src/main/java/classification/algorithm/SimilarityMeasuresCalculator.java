package classification.algorithm;

public class SimilarityMeasuresCalculator {
    public static float wordSimilarity(String firstWord, String secondWord) {
        return firstWord.length() * 1.0f / secondWord.length();
    }
}
