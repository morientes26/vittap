package com.innovatrics.commons.vittap.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * example : matt class, tango class
 */
@Entity
public class ClassCategory {

    @Id
    @GeneratedValue
    private long id;

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

    public long getId() {
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