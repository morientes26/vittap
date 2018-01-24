package com.vitta_pilates.model.enumeration;

import org.zkoss.util.resource.Labels;

public enum Month {
    JANUARY(1, "january"),
    FEBRUARY(2, "february"),
    MARCH(3, "march"),
    APRIL(4, "april"),
    MAY(5, "may"),
    JUNE(6, "june"),
    JULE(7, "jule"),
    AUGUST(8, "august"),
    SEPTEMBER(9, "september"),
    OCTOBER(10, "october"),
    NOVEMBER(11, "november"),
    DECEMBER(12, "december");


    private int idx;
    private String name;

    Month(int idx, String name){
        this.name = name;
        this.idx = idx;
    }

    public static String getName(int month){
        for (Month m : Month.values()) {
           if (m.getIdx()==month)
               return m.getName();
        }
        throw new IllegalStateException("Mount has not been found");
    }

    public int getIdx() {
        return idx;
    }

    public String getName() {
        return Labels.getLabel(name);
    }

}
