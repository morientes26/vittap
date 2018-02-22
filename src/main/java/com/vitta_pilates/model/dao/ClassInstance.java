package com.vitta_pilates.model.dao;

import com.vitta_pilates.model.enumeration.ClassInstanceStatus;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.*;


@Entity
public class ClassInstance extends OccurrenceContent {

    @Id
    @GeneratedValue
    private long id;

    private Date trueTime; // (place in calendar)

    @OneToOne
    private Attendant trueAttendingTeacher;

    private String name;
    private String description;

    /** Final teacher's salary is made of all finished events. After closing salary, events become 'payed' */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ClassInstanceStatus status;

    @ManyToOne
    @JoinColumn(name="class_id")
    private Class clazz;

    public ClassInstance(){}

    public ClassInstance(Date trueTime) {
        this.trueTime = trueTime;
        this.status = ClassInstanceStatus.CREATED;
    }

    @Override
    public String toString() {
        return "ClassInstance{" +
                "id=" + id +
                ", trueTime=" + trueTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public Date getTrueTime() {
        return trueTime;
    }

    public void setTrueTime(Date trueTime) {
        this.trueTime = trueTime;
    }

    public Attendant getTrueAttendingTeacher() {
        return trueAttendingTeacher;
    }

    public void setTrueAttendingTeacher(Attendant trueAttendingTeacher) {
        this.trueAttendingTeacher = trueAttendingTeacher;
    }

    public ClassInstanceStatus getStatus() {
        return status;
    }

    public void setStatus(ClassInstanceStatus status) {
        this.status = status;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
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
}