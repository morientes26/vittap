package com.vitta_pilates.core.event.component;

import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.dao.attendance.ClassSeat;
import com.vitta_pilates.model.dao.attendance.ClassSeatSlot;
import com.vitta_pilates.model.enumeration.ClassSeatSlotStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 *
 * @author Michal Zelenak
 */
public class AttendanceHelper {

    private final Logger log = LoggerFactory.getLogger(AttendanceHelper.class);
    
    public static ClassSlotActions[] getAppliableActions(ClassSeatSlotStatus slot, boolean fixedSeat) {
        switch(slot) {
            case EMPTY:
                return new ClassSlotActions[] { ClassSlotActions.ENROLL }; // for both fixed/tmp
            case OCCUPIED:
                if(fixedSeat)
                    return new ClassSlotActions[] { ClassSlotActions.UNENROLL, ClassSlotActions.SUSPEND };
                else
                    return new ClassSlotActions[] { ClassSlotActions.UNENROLL };
            case SUSPENDED:
                if(fixedSeat)
                    return new ClassSlotActions[] { ClassSlotActions.UNSUSPEND };
                else
                    return new ClassSlotActions[] { ClassSlotActions.ENROLL };
            default:
                throw new RuntimeException("Unsupported ClassSeatSlotStatus : " + slot);
        }
    }
    
    //translate partial status into compound status for easier class seat rendering
    public static ClassSeatStatus getClassSeatStatus(ClassSeatSlotStatus fixed, ClassSeatSlotStatus tmp) {
        if( fixed.equals(ClassSeatSlotStatus.EMPTY) && tmp.equals(ClassSeatSlotStatus.EMPTY) )
            return ClassSeatStatus.EMPTY;
        else if( fixed.equals(ClassSeatSlotStatus.OCCUPIED) && tmp.equals(ClassSeatSlotStatus.EMPTY) )
            return ClassSeatStatus.ENROLLED_FIXED;
        else if( fixed.equals(ClassSeatSlotStatus.EMPTY) && tmp.equals(ClassSeatSlotStatus.OCCUPIED) )
            return ClassSeatStatus.ENROLLED_TEMPORARY;
        else if( fixed.equals(ClassSeatSlotStatus.SUSPENDED) && tmp.equals(ClassSeatSlotStatus.EMPTY) )
            return ClassSeatStatus.SUSPENDED_EMPTY;
        else if( fixed.equals(ClassSeatSlotStatus.SUSPENDED) && tmp.equals(ClassSeatSlotStatus.OCCUPIED) )
            return ClassSeatStatus.SUSPENDED_ENROLLED;
        else
            throw new RuntimeException("Unsupported ClassSeatStatus : " + fixed + " + " + tmp);
    }
    
    static Attendance createDummyAttendance() {
        Attendance att = new Attendance();
//        att.setTeacher( new ClassSeat());
//        att.getTeacher().setFixed(new ClassSeatSlot());
//        att.getTeacher().setTemporary(new ClassSeatSlot());
        att.setClassSeats(new ArrayList<>());
        ClassSeat c = new ClassSeat();
        c.setFixed(new ClassSeatSlot());
        c.setTemporary(new ClassSeatSlot());
        att.getClassSeats().add(c);

        return att;
    }
    
    public String renderSlot(boolean isLeftSide, ClassSeatSlotStatus icon, boolean enabled, ClassSlotActions[] actions) {
        if (isLeftSide){
            //
        }
        log.debug("render slot is left: {} status: {} enable: {} action: {}", isLeftSide, icon, enabled, actions);
        return icon.name();
    }

    void renderSeat(ClassSeat att) {
        ClassSeatStatus css = AttendanceHelper.getClassSeatStatus(att.getFixed().getStatus(), att.getTemporary().getStatus());
        
        log.debug(renderSlot(true, att.getFixed().getStatus(), css.isFixedClickable, AttendanceHelper.getAppliableActions(att.getFixed().getStatus(), true)));
        log.debug(renderSlot(false, att.getTemporary().getStatus(), css.isTempClickable, AttendanceHelper.getAppliableActions(att.getTemporary().getStatus(), false)));
    }
    
    public static void main(String[] args) 
    {
        AttendanceHelper helper = new AttendanceHelper();
        Attendance att = createDummyAttendance();

        //rendering GUI according to model(DB)
        //DB => for all seats: Controller.getClassSeatStatus, Controller.getAppliableActions => GUI 
//        helper.renderSeat(att.getTeacher());
//        for(int i = 0; i<att.getClassSeats().size(); i++ )
//            helper.renderSeat(att.getClassSeats().get(i));
//
        //
        //GUI => seat[i], action_X: Service.actionX(seat[i]) => DB
        //on enrollFixed
        //AttendanceService.enrollFixed(0, 
        
        //GUI <= for all seats: Controller.getClassSeatStatus, Controller.getAppliableActions <= DB
        // ...
    }

    // represents icons to render and clickability
    public enum ClassSeatStatus {
        EMPTY(true,true), // both can be clicked
        ENROLLED_FIXED(true,false), // only fixed can be clicked
        ENROLLED_TEMPORARY(false,true), // only temp can be clicked
        SUSPENDED_EMPTY(true,true), // both can be clicked
        SUSPENDED_ENROLLED(false,true); // only temp can be clicked

        public final boolean isFixedClickable;
        public final boolean isTempClickable;

        ClassSeatStatus(boolean isFixedEnabled, boolean isTempEnabled) {
            this.isFixedClickable = isFixedEnabled;
            this.isTempClickable = isTempEnabled;
        }
    }

    //represents buttons to show
    public enum ClassSlotActions {
        ENROLL, UNENROLL, SUSPEND, UNSUSPEND;
    }
}



/// SERVICES (change the DB content)
//
//class AttendanceService {
//    public static void enrollFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
//        //from selected date until end date of the course
//            //add Attendant ID into ClassSeat on fixed slot position
//
//        //neprida tu istu osobu viackrat
//        //kontrola pretekania (capacity exceeded on dd/MM/yyyy)
//    }
//    public static void unenrollFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
//        //from selected date
//        //get the ClassSeat ID from the given attendance
//            //remove Attendant ID if exist
//
//        //check if emptied Class/ClassInstance
//    }
//
//    public static void enrollTemporary(ClassSeat seat, Attendant attendant, boolean isTeacher) {
//    }
//    public static void unenrollTemporary(ClassSeat seat, Attendant attendant, boolean isTeacher) {
//    }
//
//    public static void suspendFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
//        //Attendance->pupils[i]->fixed = ClassSeatSlotStatus.SUSPENDED
//    }
//    public static void unsuspendFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
//        //Attendance->pupils[i]->fixed = ClassSeatSlotStatus.OCCUPIED
//    }
//
//    public static int getEmptySeats(ClassInstance cl) {
//        //for all seats in attendance, list those that are not in state EMPTY or SUSPENDED_EMPTY
//
//        return 0;
//    }
//
//    public static int getEmptyFixedSeats() {
//        //for all seats in attendance, list those that are not in state EMPTY or SUSPENDED_EMPTY and is fixed
//
//        return 0;
//    }
//}


//
///// DATA MODEL
//
//// full attendance status of one ClassInstance
//class Attendance {
//    ClassSeat teacher;
//    List<ClassSeat> pupils;
//}
//
//// is a place in a class, can be occupied by a fixed subscription, or just temporary
//// contains also attendance status after ClassInstance execution
//class ClassSeat {
////    int position;
//    ClassSeatSlot fixed;
//    ClassSeatSlot temporary;
//    boolean attendanceStaus = false; // true = attended, false = not_attended
//}
//
//// shows who is in a given slot, and if he's not suspended
//// can be also empty
//class ClassSeatSlot {
//    Attendant attendant;
//    ClassSeatSlotStatus status = ClassSeatSlotStatus.EMPTY;
//}
//
//enum ClassSeatSlotStatus {
//    OCCUPIED, SUSPENDED, EMPTY;
//}