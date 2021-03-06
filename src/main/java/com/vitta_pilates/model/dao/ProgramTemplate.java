package com.vitta_pilates.model.dao;

import org.hibernate.validator.constraints.NotEmpty;
import org.zkoss.bind.annotation.Immutable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 1 Program is a package of various class visits with particular 
 *  cost altogether
 */
@Entity
public class ProgramTemplate implements Comparable<ProgramTemplate>{

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name can not be null")
    private String name;
    private String description;

    @OneToOne
    private Tarif tarif;

    private Date dateOfIssue; // date when the program is created
    private boolean active = false; // available to assign

    public ProgramTemplate(){}

    public ProgramTemplate(String name,
                           String description,
                           Tarif tarif,
                           List<ClassVisit> classVisit,
                           Date dateOfIssue,
                           boolean active) {

        this.name = name;
        this.description = description;
        this.tarif = tarif;
     //   this.classVisit = classVisit;
        this.dateOfIssue = dateOfIssue;
        this.active = active;
    }

    @Override
    public String toString() {
        return "ProgramTemplate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tarif=" + tarif +
                ", dateOfIssue=" + dateOfIssue +
                ", active=" + active +
                '}';
    }

    public Long getId() {
        return id;
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

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

//    public List<ClassVisit> getClassVisit() {
//        return classVisit;
//    }
//
//    public void setClassVisit(List<ClassVisit> classVisit) {
//        this.classVisit = classVisit;
//    }

    //note: For ZK8 is neccessery to add immutable annotation for Date type
    @Immutable
    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int compareTo(ProgramTemplate o) {

        if(this.id == o.id)
            return 0;
        else
            return this.id > o.id ? 1 : -1;

    }
}