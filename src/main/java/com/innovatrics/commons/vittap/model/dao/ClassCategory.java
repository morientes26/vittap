package com.innovatrics.commons.vittap.model.dao;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * example : matt class, tango class
 */
@Entity
public class ClassCategory {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name can not be null")
    private String name;
    private String description;
    private boolean accessRestriction = false;
    private boolean attendanceRequired = false;

    public ClassCategory() {
    }

    public ClassCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClassCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", accessRestriction=" + accessRestriction +
                ", attendanceRequired=" + attendanceRequired +
                '}';
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

    public boolean isAccessRestriction() {
        return accessRestriction;
    }

    public void setAccessRestriction(boolean accessRestriction) {
        this.accessRestriction = accessRestriction;
    }

    public boolean isAttendanceRequired() {
        return attendanceRequired;
    }

    public void setAttendanceRequired(boolean attendanceRequired) {
        this.attendanceRequired = attendanceRequired;
    }
}