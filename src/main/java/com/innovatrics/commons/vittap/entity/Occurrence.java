package com.innovatrics.commons.vittap.entity;

import java.util.Date;

/** handle to any Calendar event */
@Deprecated
//NOTE: Don't use this object.
//NOTE: It will be better to use LocalDateTime instead of using time and date separately
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
