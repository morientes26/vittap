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

    @OneToMany(mappedBy = "attendant")
    private List<Program> programs = new ArrayList<>();

    //note: I suggest move Skills to User or Personal Data
    @OneToMany(mappedBy = "attendant")
    private List<Skill> skills = new ArrayList<>();

    @OneToOne
    private User user; // optional

    @ManyToMany(mappedBy = "attendedPupils")
    private Set<ClassInstance> classInstance;


    //note: it should be in service layer together with persistance operation
    public void registerUser(User user) {
        this.user = user;
    }

    public Attendant(){}

    public Attendant(PersonalData personalData/* , ProgramInstance[] programs */) {
        this.personalData = personalData;
        
//        this.skills.add(new Skill(Cathegory.MAT, Level.FIRSTCOMMER));
//        this.skills.add(new Skill(Cathegory.SUSPENSUS, Level.FIRSTCOMMER));
//        this.skills.add(new Skill(Cathegory.REFORMER, Level.FIRSTCOMMER));
    }

    //note: it should be in service layer together with persistance operation
    public void buyCourse( Program program ) {
        this.programs.add(program);
    }

    //note: it should be in service layer together with persistance operation
    public void addSkill(Skill newSkill) {
        for (int i = 0; i < skills.size(); i++) {
            Skill s = skills.get(i);
            if( s.getCategory() == newSkill.getCategory() )
                skills.set(i, newSkill);
        }
    }

    //FIXME: It is better to use ORM approach than 3 for cycles
    public List<ClassInstance> listAllPupilAttendances(Map<String, com.vitta_pilates.model.dao.Class> managedEvents) {
        List<ClassInstance> result = new ArrayList<>();
        
        // for all managed events
        for (Map.Entry<String, com.vitta_pilates.model.dao.Class> entry : managedEvents.entrySet()) {
            // for all event instances
            List<ClassInstance> instances = entry.getValue().getInstances();
            for (ClassInstance instance : instances) {
                // list those where this attendant is listed
                for (Attendant pupil : instance.getAttendedPupils()) {
                    if( pupil.personalData.getName().equalsIgnoreCase(personalData.getName()) ) {
                        result.add(instance);
                        break;
                    }
                }
            }
        }
        
        return result;
    }

    //FIXME: It is better to use ORM approach than 3 for cycles
    public List<ClassInstance> listAllTeacherAttendances(Map<String, com.vitta_pilates.model.dao.Class> managedEvents) {
        List<ClassInstance> result = new ArrayList<>();
        
        // for all managed events
        for (Map.Entry<String, com.vitta_pilates.model.dao.Class> entry : managedEvents.entrySet()) {
            // for all event instances
            List<ClassInstance> instances = entry.getValue().getInstances();
            for (ClassInstance instance : instances) {
                // list those where this attendant is listed
                Attendant teacher = instance.getTrueAttendingTeacher();
                if( teacher.personalData.getName().equalsIgnoreCase(personalData.getName()) ) {
                    result.add(instance);
                }
            }
        }
        
        return result;
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


    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ClassInstance> getClassInstance() {
        return classInstance;
    }

    public void setClassInstance(Set<ClassInstance> classInstance) {
        this.classInstance = classInstance;
    }
}