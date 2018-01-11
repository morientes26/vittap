package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
public class ClassInstance extends OccurrenceContent {

    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime trueTime; // (place in calendar)

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Attendant trueAttendingTeacher;

    @OneToMany(mappedBy = "classInstance")
    private List<Attendant> attendedPupils;

    /** Final teacher's salary is made of all finished events. After closing salary, events become 'payed' */
    private ClassInstanceStatus status;

    @ManyToOne
    @JoinColumn(name="class_id", nullable=false)
    private Class clazz;

    public ClassInstance(){}

    public ClassInstance(LocalDateTime trueTime) {
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

    public long getId() {
        return id;
    }

    public LocalDateTime getTrueTime() {
        return trueTime;
    }

    public void setTrueTime(LocalDateTime trueTime) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
     //   sb.append("Date : ").append(TimeUtils.toString(trueTime.date)).append(" ").append(TimeUtils.toString(trueTime.time)).append('\n');
        sb.append("\tAttendance [").append(trueAttendingTeacher.getPersonalData().getName()).append(" : ");
        for (Attendant pupil : attendedPupils) {
            sb.append(pupil.getPersonalData().getName()).append(", ");
        }
        return sb.append("]").toString();
    }
}