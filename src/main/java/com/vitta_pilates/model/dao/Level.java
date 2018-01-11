package com.vitta_pilates.model.dao;

// TODO:2017-01-14:mze: hierarchy of different class types

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Level {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    public Level(){}

    public Level(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId(){
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

    // pupil
//    FIRSTCOMMER,//(null,null),
//    BEGGINER,//(null,null),
//    INTERMEDIATE,//(null,null),
//    ADVANCED,//(null,null),
//
//    // instructor
//    TEACHER_FOR_BEGGINERS,//(null,null),
//    TEACHER_FOR_INTERMEDIATES,//(null,null),
//    TEACHER_FOR_ADVANCED,//(null,null),
//
//    // instructors trainer
//    TEACHER_OF_TEACHERS_FOR_BEGGINERS,//(null,null),
//    TEACHER_OF_TEACHERS_FOR_INTERMEDIATES,//(null,null),
//    TEACHER_OF_TEACHERS_FOR_ADVANCED,//(null,null),
//
//    // instructors trainer 2nd level
//    MASTER_TEACHER,//(null,null),
//    INTERNATIONAL_MASTER_TEACHER,//(null,null);
    
//    public final List<EventType> attendableClasses;
//    public final List<EventType> conductableClasses;
//    
//    Level(List<EventType> attendableClasses, List<EventType> conductableClasses) {
//        this.attendableClasses = attendableClasses;
//        this.conductableClasses = conductableClasses;
//    }

}
