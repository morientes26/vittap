package com.vitta_pilates.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonalData {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String additionalData;

    public PersonalData(){}

    public PersonalData(String name) {
        this.name = name;
    }

    public PersonalData(String name, String additionalData) {
        this.name = name;
        this.additionalData = additionalData;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }
}