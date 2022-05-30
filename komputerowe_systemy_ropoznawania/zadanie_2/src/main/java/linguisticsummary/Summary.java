package linguisticsummary;

import linguisticsummary.variables.Quantifier;

import java.util.List;

public class Summary {
    private final Quantifier quantifier;
    private final Qualifier qualifier;
    private final Summarizer summarizer;

    public Summary(Quantifier quantifier, Qualifier qualifier, Summarizer summarizer) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
    }

    public String linguinize(List<Meal> meals) {
        if(qualifier == null){
            return quantifier.getLabel() + " of meals are " + summarizer.linguinize() + "  [" + degreeOfTruth(meals) + "]  ";
        }
        return quantifier.getLabel() + qualifier.linguinize() + " of meals are " + summarizer.linguinize() + "  [" + degreeOfTruth(meals) + "]  ";
    }


    private double degreeOfTruth(List<Meal> meals) {
        if(qualifier == null){
            if (quantifier.isAbsolute()) {
                return quantifier.getMembership().apply(summarizer.sigmaCount(meals));
            } else {
                return quantifier.getMembership().apply(summarizer.sigmaCount(meals) / meals.size());
            }
        } else {
            double qualifierSigmaCount = qualifier.sigmaCount(meals);
            if(qualifierSigmaCount == 0){
                return 0;
            }
            return quantifier.getMembership().apply(summarizer.sigmaCount(meals)/qualifierSigmaCount);
        }

    }

    private double degreeOfFuziness() {
        if (qualifier == null){

        }
        return 0;
    }

    private double degreeOfImprecision() {
        return 0;
    }

    private double degreeOfCovering() {
        return 0;
    }

    private double degreeOfAppropriateness() {
        return 0;
    }

    private double lengthOfSummary() {
        return 0;
    }

    private double degreeOfQuantifierImprecision() {
        return 0;
    }

    private double degreeOfQuantifierCardinality() {
        return 0;
    }

    private double degreeOfSummarizerCardinality() {
        return 0;
    }

    private double degreeOfQualifierImprecision() {
        return 0;
    }

    private double degreeOfQualifierCardinality() {
        return 0;
    }

    private double optimalSummary() {
        return 0;
    }
}
