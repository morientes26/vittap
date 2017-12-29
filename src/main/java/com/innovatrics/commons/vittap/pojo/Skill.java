package com.innovatrics.commons.vittap.pojo;

public class Skill {
    public Skill(ClassCategory cathegory, Level level) {
        this.cathegory = cathegory;
        this.level = level;
    }
    
    public void setLevel( Level level ) {
        this.level = level;
    }
    
    public ClassCategory cathegory;    
    public Level level;
}