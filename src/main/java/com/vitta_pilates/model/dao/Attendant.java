package com.vitta_pilates.model.dao;

import javax.persistence.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Through declared class types teachers can be listed as available
 *  of conducting a particular classroom event
 */
@Entity
public class Attendant {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private PersonalData personalData;
    private String additionalData; //health data, working experience, ...

    @OneToMany(mappedBy = "attendant")
    private List<Program> programs = new ArrayList<>();

    @OneToMany(mappedBy = "attendant")
    private List<Skill> skills = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private User user; // optional

    @ManyToOne
    @JoinColumn(name="class_id", nullable=false)
    private com.vitta_pilates.model.dao.Class clazz;

    @ManyToOne
    @JoinColumn(name="class_instance_id", nullable=false)
    private ClassInstance classInstance;


    public void registerUser(User user) {
        this.user = user;
    }

    public Attendant(){}

    public Attendant(PersonalData personalData, String additionalData/* , ProgramInstance[] programs */) {
        this.personalData = personalData;
        this.additionalData = additionalData;
        
//        this.skills.add(new Skill(Cathegory.MAT, Level.FIRSTCOMMER));
//        this.skills.add(new Skill(Cathegory.SUSPENSUS, Level.FIRSTCOMMER));
//        this.skills.add(new Skill(Cathegory.REFORMER, Level.FIRSTCOMMER));
    }
    public void buyCourse( Program program ) {
        this.programs.add(program);
    }
    
    public void addSkill(Skill newSkill) {
        for (int i = 0; i < skills.size(); i++) {
            Skill s = skills.get(i);
            if( s.getCategory() == newSkill.getCategory() )
                skills.set(i, newSkill);
        }
    }
    
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


    public long getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
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

    public com.vitta_pilates.model.dao.Class getClazz() {
        return clazz;
    }

    public void setClazz(com.vitta_pilates.model.dao.Class clazz) {
        this.clazz = clazz;
    }

    public ClassInstance getClassInstance() {
        return classInstance;
    }

    public void setClassInstance(ClassInstance class_instance) {
        this.classInstance = class_instance;
    }
}