package com.vitta_pilates.model.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Michal Zelenak
 */
@Entity
public class Reminder extends OccurrenceContent {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String details;

    public Reminder(){}

    public Reminder(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
