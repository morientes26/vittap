package com.vitta_pilates.model.dao;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * example : matt class beginers 1 hour
 */
//TODO: I suggest name of this class 'EventTemplate' or 'Box'
@Entity
public class ClassTemplate {

    @Id
    @GeneratedValue
    private Long id;


    @NotEmpty(message = "Name can not be null")
    private String name;
    private String description;

    @OneToOne
    private ClassCategory type; // (class, custom)

    private Integer duration = 60; // in minutes
    private int capacity; // optional : capacity override due to room restrictions or else

    @OneToOne
    private Level requiredLevel; // optional

    public ClassTemplate() {
    }

    public ClassTemplate(String name, String description, ClassCategory type) {
        this.name = name;
        this.description = description;
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClassCategory getType() {
        return type;
    }

    public void setType(ClassCategory type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Level getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Level requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
}