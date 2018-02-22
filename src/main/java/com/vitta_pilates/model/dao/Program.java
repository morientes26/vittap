package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
public class Program {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private ProgramTemplate programTemplate; // type

    @OneToOne
    private Schedule schedule; // validity : to generate/schedule ProgramInstances

    @OneToMany(mappedBy = "program")
    private Set<ProgramInstance> programInstances;

    private Date dateOfIssue; // date when the program is given to a pupil
    private BigDecimal discount; // by default 0
    private boolean active; // scheduled

    public Program(){}

    public Program(Schedule schedule, ProgramTemplate programTemplate, Date dateOfIssue, BigDecimal discount) {
        this.schedule = schedule;
        this.programTemplate = programTemplate;
        this.dateOfIssue = dateOfIssue;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                '}';
    }

    public long getId() {
        return id;
    }

    public ProgramTemplate getProgramTemplate() {
        return programTemplate;
    }

    public void setProgramTemplate(ProgramTemplate programTemplate) {
        this.programTemplate = programTemplate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Set<ProgramInstance> getProgramInstances() {
        return programInstances;
    }

    public void setProgramInstances(Set<ProgramInstance> programInstances) {
        this.programInstances = programInstances;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}