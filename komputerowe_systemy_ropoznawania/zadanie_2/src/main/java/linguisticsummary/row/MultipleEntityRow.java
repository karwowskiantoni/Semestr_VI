package linguisticsummary.row;

public class MultipleEntityRow implements Row {
  private final String summary;
  private final String T1;

  public MultipleEntityRow(String summary, String t1) {
    this.summary = summary;
    T1 = t1;
  }

  public String getSummary() {
    return summary;
  }

  public String getT1() {
    return T1;
  }
}
