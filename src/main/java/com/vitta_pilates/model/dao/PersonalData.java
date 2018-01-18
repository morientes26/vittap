package com.vitta_pilates.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PersonalData {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String additionalData;

    private boolean active;  // active or suspend

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}