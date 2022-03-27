package classificator.model.internal;

public enum Label {
  WEST_GERMANY("west-germany"),
  USA("usa"),
  FRANCE("france"),
  UK("uk"),
  CANADA("canada"),
  JAPAN("japan");
  public final String literal;

  public static Label valueOfLiteral(String literal) {
    for (Label label : values()) {
      if (label.literal.equals(literal)) {
        return label;
      }
    }
    return null;
  }

  private Label(String literal) {
    this.literal = literal;
  }
}
