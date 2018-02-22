package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Through declared class types teachers can be listed as available
 *  of conducting a particular classroom event
 */
@Entity
public class Attendant {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonalData personalData;

    //note: I suggest move Skills to UserAccount or Personal Data
    @OneToMany(mappedBy = "attendant")
    private List<Skill> skills = new ArrayList<>();

    @OneToOne
    private UserAccount userAccount; // optional

    @ManyToMany(mappedBy = "attendedPupils")
    private Set<ProgramInstance> programInstances;

    private boolean pupil = true;


    //note: it should be in service layer together with persistance operation
    public void registerUser(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Attendant(){}

    public Attendant(PersonalData personalData/* , ProgramInstance[] programs */) {
        this.personalData = personalData;
    }

    //note: it should be in service layer together with persistance operation
    public void addSkill(Skill newSkill) {
        for (int i = 0; i < skills.size(); i++) {
            Skill s = skills.get(i);
            if( s.getCategory() == newSkill.getCategory() )
                skills.set(i, newSkill);
        }
    }

    @Override
    public String toString() {
        return "Attendant{" +
                "id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Set<ProgramInstance> getProgramInstances() {
        return programInstances;
    }

    public void setProgramInstances(Set<ProgramInstance> programInstances) {
        this.programInstances = programInstances;
    }

    public boolean isPupil() {
        return pupil;
    }

    public void setPupil(boolean pupil) {
        this.pupil = pupil;
    }
}