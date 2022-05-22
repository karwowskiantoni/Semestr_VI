package membership;

public final class MembershipLabel {

    public enum SatietyMembershipLabel {
        VERY_LOW_CALORIE, LOW_CALORIE, MEDIUM_CALORIE, HIGH_CALORIE, EXTREMELY_HIGH_CALORIE
    }

    public enum WaterinessMembershipLabel {
        ALMOST_WITHOUT_WATER, HYDRATING, WATER_RICH, ENTIRELY_WATER
    }

    public enum AbsorptionMembershipLabel {
        LITTLE_DIGESTED, MEDIOCRE_DIGESTED, FULLY_DIGESTED,
    }

    public enum ProteinContentMembershipLabel {
        LITTLE_PROTEIN, MEDIOCRE_PROTEIN, HIGH_PROTEIN
    }

    public enum FatnessMembershipLabel {
        ALMOST_WITHOUT_FAT, LITTLE_FAT, FAT, HIGH_FAT, EXTREMELY_FAT
    }

    public enum SweetnessMembershipLabel {
        NOT_SWEET, SWEET, VERY_SWEET, MADE_OF_SUGAR
    }

    public enum AntidepressivenessMembershipLabel {
        NO_IMPACT_ON_DEPRESSION, LITTLE_IMPACT_ON_DEPRESSION, IMPACTFUL_ON_DEPRESSION, HIGHLY_ANTIDEPRESSANT
    }

    public enum HealthinessMembershipLabel {
        UNHEALTHY, HEALTHY, HEALTHFUL
    }

    public enum DigestionSpeedMembershipLabel {
        SLUGGISH, SLOW, STANDARD, FAST, RAPID
    }

    public enum AdaptationForDiabeticsMembershipLabel {
        AVOIDABLE, NEUTRAL, DESTINED_FOR_DIABETICS
    }

    public enum AbsoluteQuantifierMembershipLabel {
        LESS_THAN_2000, ABOUT_2500, ABOUT_5000, ABOUT_6500, MORE_THAN_7000
    }

    public enum RelativeQuantifierMembershipLabel {
        VERY_SMALL_AMOUNT, SMALL_AMOUNT, MEDIUM_AMOUNT, HIGH_AMOUNT, VERY_HIGH_AMOUNT
    }
}
