package com.innovatrics.commons.vittap.pojo;

/**
 * example : matt class beginers 1 hour
 */
public class ClassTemplate {
    public String name;
    public String description;
    public ClassCategory type; // (class, custom)
    
    public Integer duration; // in minutes 
    
    public int capacity; // optional : capacity override due to room restrictions or else    
    public Level requiredLevel; // optional
    
    public ClassTemplate(String name, String description, ClassCategory type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }
    
    // ---
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public void setRequiredLevel(Level requiredLevel) {
        this.requiredLevel = requiredLevel;
    }
}