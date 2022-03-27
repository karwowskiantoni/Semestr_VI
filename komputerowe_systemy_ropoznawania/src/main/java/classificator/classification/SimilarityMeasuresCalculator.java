package classificator.classification;

import org.apache.commons.text.similarity.LevenshteinDistance;
public class SimilarityMeasuresCalculator {

    public static int wordSimilarity(String firstWord, String secondWord) {
        return LevenshteinDistance.getDefaultInstance().apply(firstWord, secondWord);
    }
}
