package pl.ksr.view;

import java.util.ArrayList;

public class SummaryTableRecord {
    private String summary;
    private String T1;
    private String T2;
    private String T3;
    private String T4;
    private String T5;
    private String T6;
    private String T7;
    private String T8;
    private String T9;
    private String T10;
    private String T11;
    private String T;

    public SummaryTableRecord(String summary, ArrayList<String> list) {
        this.summary = summary;
        T1 = list.get(0);
        T2 = list.get(1);
        T3 = list.get(2);
        T4 = list.get(3);
        T5 = list.get(4);
        T6 = list.get(5);
        T7 = list.get(6);
        T8 = list.get(7);
        T9 = list.get(8);
        T10 = list.get(9);
        T11 = list.get(10);
        this.T = list.get(11);
    }

    public SummaryTableRecord(String summary, String t1) {
        this.summary = summary;
        T1 = t1;
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

    public String getT() {
        return T;
    }
}
