package com.innovatrics.commons.vittap.pojo;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Through declared class types teachers can be listed as available
 *  of conducting a particular classroom event
 */
public class Attendant {
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
            if( s.cathegory == newSkill.cathegory )
                skills.set(i, newSkill);
        }
    }
    
    public List<ClassInstance> listAllPupilAttendances(Map<String, Class> managedEvents) {
        List<ClassInstance> result = new ArrayList<>();
        
        // for all managed events
        for (Map.Entry<String, Class> entry : managedEvents.entrySet()) {
            // for all event instances
            List<ClassInstance> instances = entry.getValue().instances;
            for (ClassInstance instance : instances) {
                // list those where this attendant is listed
                for (Attendant pupil : instance.attendedPupils) {
                    if( pupil.personalData.name.equalsIgnoreCase(personalData.name) ) {
                        result.add(instance);
                        break;
                    }
                }
            }
        }
        
        return result;
    }
    
    public List<ClassInstance> listAllTeacherAttendances(Map<String, Class> managedEvents) {
        List<ClassInstance> result = new ArrayList<>();
        
        // for all managed events
        for (Map.Entry<String, Class> entry : managedEvents.entrySet()) {
            // for all event instances
            List<ClassInstance> instances = entry.getValue().instances;
            for (ClassInstance instance : instances) {
                // list those where this attendant is listed
                Attendant teacher = instance.trueAttendingTeacher;
                if( teacher.personalData.name.equalsIgnoreCase(personalData.name) ) {
                    result.add(instance);
                }
            }
        }
        
        return result;
    }
    
    public void registerUser(User user) {
        this.user = user;
    }

    public PersonalData personalData;
    public String additionalData; //health data, working experience, ...
    public List<Program> programs = new ArrayList<>();
    public List<Skill> skills = new ArrayList<>();
    
    public User user; // optional
}