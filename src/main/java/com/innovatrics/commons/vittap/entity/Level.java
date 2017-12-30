package com.innovatrics.commons.vittap.entity;

// TODO:2017-01-14:mze: hierarchy of different class types

public enum Level {
    // pupil
    FIRSTCOMMER,//(null,null),
    BEGGINER,//(null,null),
    INTERMEDIATE,//(null,null),
    ADVANCED,//(null,null),
       
    // instructor
    TEACHER_FOR_BEGGINERS,//(null,null),
    TEACHER_FOR_INTERMEDIATES,//(null,null),
    TEACHER_FOR_ADVANCED,//(null,null),
    
    // instructors trainer
    TEACHER_OF_TEACHERS_FOR_BEGGINERS,//(null,null),
    TEACHER_OF_TEACHERS_FOR_INTERMEDIATES,//(null,null),
    TEACHER_OF_TEACHERS_FOR_ADVANCED,//(null,null),
    
    // instructors trainer 2nd level
    MASTER_TEACHER,//(null,null),
    INTERNATIONAL_MASTER_TEACHER,//(null,null);
    
//    public final List<EventType> attendableClasses;
//    public final List<EventType> conductableClasses;
//    
//    Level(List<EventType> attendableClasses, List<EventType> conductableClasses) {
//        this.attendableClasses = attendableClasses;
//        this.conductableClasses = conductableClasses;
//    }
}