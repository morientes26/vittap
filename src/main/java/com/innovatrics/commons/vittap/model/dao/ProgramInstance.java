package com.innovatrics.commons.vittap.model.dao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProgramInstance extends OccurrenceContent {

    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime trueTime; // (place in calendar)
    private ProgramInstanceStatus status;

    @ManyToOne
    @JoinColumn(name="program_id", nullable=false)
    private Program program;

    public ProgramInstance(){}

    public ProgramInstance(LocalDateTime trueTime, ProgramInstanceStatus status) {
        this.trueTime = trueTime;
        this.status = status;
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
}