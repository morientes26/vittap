package com.vitta_pilates.model.dao;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

/**
 * example : matt class, tango class
 */
@Entity
public class ClassCategory {

    public static final String GRAY = "#99a3a4";
    public static final String RED = "#fadbd8";
    public static final String BLUE = "#d6eaf8";
    public static final String ORANGE = "#fae5d3";
    public static final String GREEN = "#DAF7A6";
    public static final String PEACH = "#FFC300";

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name can not be null")
    private String name;
    private String description;
    private String color = PEACH;
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
                ", color='" + color + '\'' +
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static List<String> getAvailableColors(){
        return Arrays.asList(GRAY, RED, BLUE, ORANGE, GREEN, PEACH);
    }
}