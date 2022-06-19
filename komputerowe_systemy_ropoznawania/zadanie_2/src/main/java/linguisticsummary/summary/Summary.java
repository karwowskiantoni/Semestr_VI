package linguisticsummary.summary;

import linguisticsummary.row.Row;

public interface Summary {
    String toString();
    Row toRow();

    default String formatResult(double val) {
        if (val < 0.0000009 && val > 0.0) {
            return "~0.0";
        }
        return String.valueOf(round(val));
    }

    default double round(double val){
        return Math.round(val * 100.0) / 100.0;
    }
}
