package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ProgramInstance extends OccurrenceContent {

    @Id
    @GeneratedValue
    private long id;

    private Date trueTime; // (place in calendar)

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProgramInstanceStatus status;

    @ManyToOne
    private Program program;

    private String notes;

    @ManyToMany
    @JoinTable(
            name = "program_instance_attendent",
            joinColumns = {@JoinColumn(name = "program_instance_id")},
            inverseJoinColumns = {@JoinColumn(name = "attendent_id")}
    )
    List<Attendant> attendedPupils = new ArrayList<>();

    @OneToOne
    private Attendant trueAttendingTeacher;

    public ProgramInstance(){}

    public ProgramInstance(Date trueTime, ProgramInstanceStatus status) {
        this.trueTime = trueTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProgramInstance{" +
                "id=" + id +
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

    public ProgramInstanceStatus getStatus() {
        return status;
    }

    public void setStatus(ProgramInstanceStatus status) {
        this.status = status;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Attendant> getAttendedPupils() {
        return attendedPupils;
    }

    public void setAttendedPupils(List<Attendant> attendedPupils) {
        this.attendedPupils = attendedPupils;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Attendant getTrueAttendingTeacher() {
        return trueAttendingTeacher;
    }

    public void setTrueAttendingTeacher(Attendant trueAttendingTeacher) {
        this.trueAttendingTeacher = trueAttendingTeacher;
    }
}