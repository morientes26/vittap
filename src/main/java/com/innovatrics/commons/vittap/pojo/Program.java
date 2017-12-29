package com.innovatrics.commons.vittap.pojo;

import java.util.Date;
import java.util.List;

public class Program {
    public Program(Schedule schedule, ProgramTemplate program, Date dateOfIssue, Double discount) {
        this.schedule = schedule;
        this.program = program;
        this.dateOfIssue = dateOfIssue;
        this.discount = discount;
    }
    public ProgramTemplate program; // type
    public Schedule schedule; // validity : to generate/schedule ProgramInstances
    public List<ProgramInstance> instances;
    public Date dateOfIssue; // date when the program is given to a pupil
    public Double discount; // by default 0
    public boolean active; // scheduled
}