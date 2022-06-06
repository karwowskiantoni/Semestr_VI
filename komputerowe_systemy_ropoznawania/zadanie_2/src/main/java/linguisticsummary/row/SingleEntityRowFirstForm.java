package linguisticsummary.row;

public class SingleEntityRowFirstForm implements Row {
  private final String summary;
  private final String T1;
  private final String T2;
  private final String T3;
  private final String T4;
  private final String T5;
  private final String T6;
  private final String T7;
  private final String T8;
  private final String optimal;

  public SingleEntityRowFirstForm(
      String summary,
      String t1,
      String t2,
      String t3,
      String t4,
      String t5,
      String t6,
      String t7,
      String t8,
      String optimal) {
    this.summary = summary;
    T1 = t1;
    T2 = t2;
    T3 = t3;
    T4 = t4;
    T5 = t5;
    T6 = t6;
    T7 = t7;
    T8 = t8;
    this.optimal = optimal;
  }

  public String getSummary() {
    return summary;
  }

  public String getT1() {
    return T1;
  }

  public String getT2() {
    return T2;
  }

  public String getT3() {
    return T3;
  }

  public String getT4() {
    return T4;
  }

  public String getT5() {
    return T5;
  }

  public String getT6() {
    return T6;
  }

  public String getT7() {
    return T7;
  }

  public String getT8() {
    return T8;
  }

  public String getOptimal() {
    return optimal;
  }
}
