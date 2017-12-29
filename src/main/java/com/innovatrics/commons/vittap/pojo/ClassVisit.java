/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovatrics.commons.vittap.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * single class type entrance
 */
public class ClassVisit
{
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
    public ClassCategory classType;
    public int number;
}