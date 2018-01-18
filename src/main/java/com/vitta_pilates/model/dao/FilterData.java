package com.vitta_pilates.model.dao;

import org.zkoss.util.resource.Labels;

/**
 * Use in pupils and teachers filter critery
 */
public enum FilterData {
    OPEN_DEBTS("filterdata.opendebts"),
    NON_ENROLLED("filterdata.nonenrolled"),
    ALL("filterdata.all");


    private String name;

    FilterData(String name){
        this.name = name;
    }

    public String getName() {
        return Labels.getLabel(name);
    }

}