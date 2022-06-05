package linguisticsummary.model;

public final class Meal {
    private final String name;
    private final double satiety;
    private final double wateriness;
    private final double absorption;
    private final double proteinContent;
    private final double fatness;
    private final double sweetness;
    private final double antidepressiveness;
    private final double healthiness;
    private final double digestionSpeed;
    private final double adaptationForDiabetics;

    public Meal(String name,
                double satiety,
                double wateriness,
                double absorption,
                double proteinContent,
                double fatness,
                double sweetness,
                double antidepressiveness,
                double healthiness,
                double digestionSpeed,
                double adaptationForDiabetics) {
        this.name = name;
        this.satiety = satiety;
        this.wateriness = wateriness;
        this.absorption = absorption;
        this.proteinContent = proteinContent;
        this.fatness = fatness;
        this.sweetness = sweetness;
        this.antidepressiveness = antidepressiveness;
        this.healthiness = healthiness;
        this.digestionSpeed = digestionSpeed;
        this.adaptationForDiabetics = adaptationForDiabetics;
    }

    public String name() {
        return name;
    }

    public double satiety() {
        return satiety;
    }

    public double wateriness() {
        return wateriness;
    }

    public double absorption() {
        return absorption;
    }

    public double proteinContent() {
        return proteinContent;
    }

    public double fatness() {
        return fatness;
    }

    public double sweetness() {
        return sweetness;
    }

    public double antidepressiveness() {
        return antidepressiveness;
    }

    public double healthiness() {
        return healthiness;
    }

    public double digestionSpeed() {
        return digestionSpeed;
    }

    public double adaptationForDiabetics() {
        return adaptationForDiabetics;
    }
}
