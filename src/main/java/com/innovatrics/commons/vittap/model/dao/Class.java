package com.innovatrics.commons.vittap.model.dao;

import javax.persistence.*;
import java.util.List;

/**
 * scheduled Event (1 Created repeatable event in the schedule)
 */
@Entity
public class Class {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Schedule schedule; // when

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Room room; // optional : where (place, capacity)

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ClassTemplate event; // what

    private boolean active; // scheduled

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Attendant conductingTeacher; // optional : enrolled teacher

    @OneToMany(mappedBy = "clazz")
    private List<Attendant> enrolledPupils; // optional : enrolled pupils

    @OneToMany(mappedBy = "clazz")
    private List<ClassInstance> instances; // result of Event Schedule


    public Class(Schedule schedule, ClassTemplate event)
    {
        this.schedule = schedule;
        this.event = event;
    }
    
    public ClassInstance addEvent(ClassInstance event) {
        this.instances.add(event);
        return event;
    }


    public long getId() {
        return id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ClassTemplate getEvent() {
        return event;
    }

    public void setEvent(ClassTemplate event) {
        this.event = event;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Attendant getConductingTeacher() {
        return conductingTeacher;
    }

    public void setConductingTeacher(Attendant conductingTeacher) {
        this.conductingTeacher = conductingTeacher;
    }

    public List<Attendant> getEnrolledPupils() {
        return enrolledPupils;
    }

    public void setEnrolledPupils(List<Attendant> enrolledPupils) {
        this.enrolledPupils = enrolledPupils;
    }

    public List<ClassInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<ClassInstance> instances) {
        this.instances = instances;
    }
}