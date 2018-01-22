package com.vitta_pilates.model.dao;

import org.zkoss.util.resource.Labels;

public enum ReccurenceType {
    ONE_TIME("reccurence.onetime"),
    DAILY("reccurence.dayly"),
    WEEKLY("reccurence.weekly"),
    MONTHY("reccurence.monthly"),
    YEARLY("reccurence.yearly");

    private String name;

    ReccurenceType(String name){
        this.name = name;
    }

    public String getName() {
        return Labels.getLabel(name);
    }

}
