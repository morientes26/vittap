package com.innovatrics.commons.vittap.pojo;

import java.util.Date;
import java.util.List;

/**
 * 1 Program is a package of various class visits with particular 
 *  cost altogether
 */
public class ProgramTemplate {
    public ProgramTemplate(String name, String description, Tarif tarif, List<ClassVisit> classVisit, Date dateOfIssue, boolean active) {
        this.name = name;
        this.description = description;
        this.tarif = tarif;
        this.classVisit = classVisit;
        this.dateOfIssue = dateOfIssue;
        this.active = active;
    }
    public String name;
    public String description;
    public Tarif tarif;
    public List<ClassVisit> classVisit; // singles, compounds, upd instances (since ddmmYY new cost)
    public Date dateOfIssue; // date when the program is created
    public boolean active; // available to assign
}