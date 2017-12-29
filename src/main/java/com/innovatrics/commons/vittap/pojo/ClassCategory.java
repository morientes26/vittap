package com.innovatrics.commons.vittap.pojo;

/**
 * example : matt class, tango class
 */
public class ClassCategory 
{
    public ClassCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String name;
    public String description;
    
    public boolean accessRestriction = false;
    public boolean attendanceRequired = false;
}