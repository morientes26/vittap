package com.innovatrics.commons.vittap.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * scheduled Event (1 Created repeatable event in the schedule)
 */
public class Class {
    public Class(Schedule schedule, ClassTemplate event)
    {
        this.schedule = schedule;
        this.event = event;
    }
    
    public Schedule schedule; // when
    public Room room; // optional : where (place, capacity)    
    
    public ClassTemplate event; // what
    public boolean active; // scheduled
    
    
    // ---
    
    public Attendant conductingTeacher; // optional : enrolled teacher
    public List<Attendant> enrolledPupils = new ArrayList(); // optional : enrolled pupils
    
    public List<ClassInstance> instances = new ArrayList(); // result of Event Schedule
    
    
    // ---
    
    public ClassInstance addEvent(ClassInstance event) {
        this.instances.add(event);
        return event;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void enrollTeacher(Attendant teacher) {
        this.conductingTeacher = teacher;
    }
    
    public void enrollPupil(Attendant pupil) {
        enrolledPupils.add(pupil);
    }
}