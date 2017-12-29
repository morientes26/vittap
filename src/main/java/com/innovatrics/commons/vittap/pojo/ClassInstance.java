package com.innovatrics.commons.vittap.pojo;

//import com.innovatrics.commons.vittapapp.utils.TimeUtils;
import java.util.Arrays;
import java.util.List;

public class ClassInstance extends OccurrenceContent {
    public ClassInstance(Occurrence trueTime) {
        this.trueTime = trueTime;
        this.status = ClassInstanceStatus.CREATED;
    }
    
    public Occurrence trueTime; // (place in calendar)
    public Attendant trueAttendingTeacher;
    public List<Attendant> attendedPupils;
    
    /** Final teacher's salary is made of all finished events. After closing salary, events become 'payed' */
    public ClassInstanceStatus status;
    
    public void execute(Attendant teacher, Attendant... pupils) {
        //this.attendance = new ClassroomAttendance(new Date(), teacher, pupils);
        this.trueAttendingTeacher = teacher;
        this.attendedPupils = Arrays.asList(pupils);
        this.status = ClassInstanceStatus.EXECUTED;
    }
    
    public void enclose() {
        // TODO:2017-03-09:mze: impl
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
     //   sb.append("Date : ").append(TimeUtils.toString(trueTime.date)).append(" ").append(TimeUtils.toString(trueTime.time)).append('\n');
        sb.append("\tAttendance [").append(trueAttendingTeacher.personalData.name).append(" : ");
        for (Attendant pupil : attendedPupils) {
            sb.append(pupil.personalData.name).append(", ");
        }
        return sb.append("]").toString();
    }
}