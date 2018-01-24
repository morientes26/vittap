package com.vitta_pilates.model.enumeration;

import org.zkoss.util.resource.Labels;

/**
 * Use in pupils and teachers filter critery
 */
public enum FilterTeacherData {
    PENDING_SALARIES("filterteacherdata.pending"),
    ALL("filterteacherdata.all");


    private String name;

    FilterTeacherData(String name){
        this.name = name;
    }

    public String getName() {
        return Labels.getLabel(name);
    }

}