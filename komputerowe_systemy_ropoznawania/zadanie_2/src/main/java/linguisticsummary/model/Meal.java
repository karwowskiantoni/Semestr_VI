package linguisticsummary.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Meal) obj;
        return Objects.equals(this.name, that.name) &&
                Double.doubleToLongBits(this.satiety) == Double.doubleToLongBits(that.satiety) &&
                Double.doubleToLongBits(this.wateriness) == Double.doubleToLongBits(that.wateriness) &&
                Double.doubleToLongBits(this.absorption) == Double.doubleToLongBits(that.absorption) &&
                Double.doubleToLongBits(this.proteinContent) == Double.doubleToLongBits(that.proteinContent) &&
                Double.doubleToLongBits(this.fatness) == Double.doubleToLongBits(that.fatness) &&
                Double.doubleToLongBits(this.sweetness) == Double.doubleToLongBits(that.sweetness) &&
                Double.doubleToLongBits(this.antidepressiveness) == Double.doubleToLongBits(that.antidepressiveness) &&
                Double.doubleToLongBits(this.healthiness) == Double.doubleToLongBits(that.healthiness) &&
                Double.doubleToLongBits(this.digestionSpeed) == Double.doubleToLongBits(that.digestionSpeed) &&
                Double.doubleToLongBits(this.adaptationForDiabetics) == Double.doubleToLongBits(that.adaptationForDiabetics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, satiety, wateriness, absorption, proteinContent, fatness, sweetness, antidepressiveness, healthiness, digestionSpeed, adaptationForDiabetics);
    }

    @Override
    public String toString() {
        return "Meal[" +
                "name=" + name + ", " +
                "satiety=" + satiety + ", " +
                "wateriness=" + wateriness + ", " +
                "absorption=" + absorption + ", " +
                "proteinContent=" + proteinContent + ", " +
                "fatness=" + fatness + ", " +
                "sweetness=" + sweetness + ", " +
                "antidepressiveness=" + antidepressiveness + ", " +
                "healthiness=" + healthiness + ", " +
                "digestionSpeed=" + digestionSpeed + ", " +
                "adaptationForDiabetics=" + adaptationForDiabetics + ']';
    }
}
