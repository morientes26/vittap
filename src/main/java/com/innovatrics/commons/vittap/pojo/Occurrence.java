package com.innovatrics.commons.vittap.pojo;

import java.util.Date;

/** handle to any Calendar event */
public class Occurrence {
    public Time time;
    public Date date;
    
    //visibility
    //notification set
    
    public OccurrenceType type; // distinction
    public OccurrenceContent content; // FK
    
    public Occurrence(Date date, Time time) {
        this.date = date;
        this.time = time;
    }
}
