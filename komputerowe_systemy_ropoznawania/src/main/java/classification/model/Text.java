package classification.model;

public record Text(Label correctLabel, Label predictedLabel, Vector vector) { }
