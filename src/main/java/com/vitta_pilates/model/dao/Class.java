package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * scheduled Box (1 Created repeatable event in the schedule)
 */
@Entity
public class Class {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Schedule schedule; // when vsetko cez kalendar

    @OneToOne
    private Room room; // optional : where (place, capacity)

    @OneToOne
    private ClassTemplate event; // what

    private boolean active; // scheduled

    @OneToOne
    private Attendant conductingTeacher; // optional : enrolled teacher

    @OneToMany(mappedBy = "clazz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ClassInstance> instances; // result of Box Schedule

    public Class(){
    }

    public Class(Schedule schedule, ClassTemplate event)
    {
        this.schedule = schedule;
        this.event = event;
    }
    
    public ClassInstance addEvent(ClassInstance event) {
        if (instances == null)
            this.instances = new ArrayList<>();
        this.instances.add(event);
        return event;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                '}';
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


    public List<ClassInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<ClassInstance> instances) {
        this.instances = instances;
    }
}