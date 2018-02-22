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

    @ManyToMany
    @JoinTable(
            name = "class_instance_attendent",
            joinColumns = {@JoinColumn(name = "class_instance_id")},
            inverseJoinColumns = {@JoinColumn(name = "attendent_id")}
    )
    private List<Attendant> attendedPupils = new ArrayList<>();

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

    public void execute(Attendant teacher, Attendant... pupils) {
        //this.attendance = new ClassroomAttendance(new Date(), teacher, pupils);
        this.trueAttendingTeacher = teacher;
        this.attendedPupils = Arrays.asList(pupils);
        this.status = ClassInstanceStatus.EXECUTED;
    }
    
    public void enclose() {
        // TODO:2017-03-09:mze: impl
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

    public List<Attendant> getAttendedPupils() {
        return attendedPupils;
    }

    public void setAttendedPupils(List<Attendant> attendedPupils) {
        this.attendedPupils = attendedPupils;
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