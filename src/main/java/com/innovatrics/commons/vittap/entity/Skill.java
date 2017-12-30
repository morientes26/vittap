package com.innovatrics.commons.vittap.entity;

import javax.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ClassCategory category;

    private Level level;

    @ManyToOne
    @JoinColumn(name="attendant_id", nullable=false)
    private Attendant attendant;

    public Skill(ClassCategory cathegory, Level level) {
        this.category = cathegory;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public ClassCategory getCategory() {
        return category;
    }

    public void setCategory(ClassCategory category) {
        this.category = category;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Attendant getAttendant() {
        return attendant;
    }

    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }
}