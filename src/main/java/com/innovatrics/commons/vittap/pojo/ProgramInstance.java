package com.innovatrics.commons.vittap.pojo;

public class ProgramInstance extends OccurrenceContent {
    public Occurrence trueTime; // (place in calendar)
    public ProgramInstanceStatus status;
    
    public ProgramInstance(Occurrence trueTime, ProgramInstanceStatus status) {
        this.trueTime = trueTime;
        this.status = status;
    }
}