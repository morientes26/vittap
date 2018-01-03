/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovatrics.commons.vittap.model.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * single class type entrance
 */
@Entity
public class ClassVisit {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ClassCategory classType;

    private int number;

    @ManyToOne
    @JoinColumn(name="program_template_id", nullable=false)
    private ProgramTemplate programTemplate;

    public static List<ClassVisit> create(int number, ClassCategory ...classType) {
        List<ClassVisit> visits = new ArrayList();
        for (ClassCategory ct : classType)
            visits.add( new ClassVisit(ct, number) );

        return visits;//visits.toArray(new ClassVisit[classType.length]);
    }
    
    public ClassVisit(ClassCategory classType, int number) {
        this.classType = classType;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public ClassCategory getClassType() {
        return classType;
    }

    public void setClassType(ClassCategory classType) {
        this.classType = classType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ProgramTemplate getProgramTemplate() {
        return programTemplate;
    }

    public void setProgramTemplate(ProgramTemplate programTemplate) {
        this.programTemplate = programTemplate;
    }
}