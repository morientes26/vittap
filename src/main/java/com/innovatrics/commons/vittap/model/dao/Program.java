package com.innovatrics.commons.vittap.model.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Program {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private ProgramTemplate programTemplate; // type

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private Schedule schedule; // validity : to generate/schedule ProgramInstances

    @OneToMany(mappedBy = "program")
    private Set<ProgramInstance> instances;

    private Date dateOfIssue; // date when the program is given to a pupil
    private Double discount; // by default 0
    private boolean active; // scheduled

    @ManyToOne
    @JoinColumn(name="attendant_id", nullable=true)
    private Attendant attendant;

    public Program(){}

    public Program(Schedule schedule, ProgramTemplate programTemplate, Date dateOfIssue, Double discount) {
        this.schedule = schedule;
        this.programTemplate = programTemplate;
        this.dateOfIssue = dateOfIssue;
        this.discount = discount;
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

    public Set<ProgramInstance> getInstances() {
        return instances;
    }

    public void setInstances(Set<ProgramInstance> instances) {
        this.instances = instances;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }
}