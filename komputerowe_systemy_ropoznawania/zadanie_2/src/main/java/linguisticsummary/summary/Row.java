package linguisticsummary.summary;

public class Row {
    private final String summary;
    private final String T1;
    private final String T2;
    private final String T3;
    private final String T4;
    private final String T5;
    private final String T6;
    private final String T7;
    private final String T8;
    private final String T9;
    private final String T10;
    private final String T11;
    private final String optimal;

    public Row(String summary, String t1, String t2, String t3, String t4, String t5, String t6, String t7, String t8, String t9, String t10, String t11, String optimal) {
        this.summary = summary;
        T1 = t1;
        T2 = t2;
        T3 = t3;
        T4 = t4;
        T5 = t5;
        T6 = t6;
        T7 = t7;
        T8 = t8;
        T9 = t9;
        T10 = t10;
        T11 = t11;
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

    public String getT9() {
        return T9;
    }

    public String getT10() {
        return T10;
    }

    public String getT11() {
        return T11;
    }

    public String getOptimal() {
        return optimal;
    }
}
