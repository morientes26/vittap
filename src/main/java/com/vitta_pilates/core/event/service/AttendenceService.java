package com.vitta_pilates.core.event.service;

import com.vitta_pilates.core.event.component.AttendanceForm;
import com.vitta_pilates.core.event.component.AttendanceHelper;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.dao.attendance.ClassSeat;
import com.vitta_pilates.model.enumeration.ClassSeatSlotStatus;
import com.vitta_pilates.model.repository.AttendanceRepository;
import com.vitta_pilates.model.repository.ClassInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendenceService {

  private final Logger log = LoggerFactory.getLogger(AttendenceService.class);

  @Autowired
  AttendanceRepository attendanceRepository;

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  public List<AttendanceForm> getAttendance(String classInstanceId){
    List<AttendanceForm> result = new ArrayList<>();
    ClassInstance classInstance = classInstanceRepository.findOne(Long.valueOf(classInstanceId));
    Attendance attendance = attendanceRepository.findOneByClassInstance(classInstance);
    if (attendance!=null){

//      AttendanceHelper.ClassSeatStatus status = AttendanceHelper.getClassSeatStatus(
//              attendance.getTeacher().getFixed().getStatus(),
//              attendance.getTeacher().getTemporary().getStatus()
//      );

      //todo: AttendanceHelper.getAppliableActions(attendance.getTeacher().getFixed().getStatus(), true);
      //todo: AttendanceHelper.getAppliableActions(attendance.getTeacher().getTemporary().getStatus(), false);
      AttendanceForm attendanceTeacherForm =  new AttendanceForm.AttendanceFormBuilder()
         .setId(attendance.getId())
         .setFixed(AttendanceForm.Flag.ENROLL)
         .setTemporary(AttendanceForm.Flag.EMPTY)
         .setName("test")
         .setAction(null)
         .setAction2(null)
         .createAttendanceForm();

      result.add(attendanceTeacherForm);

      for (ClassSeat pupil : attendance.getPupils()) {
        AttendanceForm attendanceForm = new AttendanceForm.AttendanceFormBuilder()
          .setId(pupil.getId())
          .setFixed(AttendanceForm.Flag.EMPTY)
          .setTemporary(AttendanceForm.Flag.EMPTY)
          .setName("test2")
          .setAction(null)
          .setAction2(null)
          .createAttendanceForm();

        result.add(attendanceForm);
      }

    }
    return result;
  }

  public void enrollFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
    //from selected date until end date of the course
    //add Attendant ID into ClassSeat on fixed slot position

    //neprida tu istu osobu viackrat
    //kontrola pretekania (capacity exceeded on dd/MM/yyyy)
    log.info("enroll fixed seat: {} attendent: {} is teacher: {}", seat, attendant, isTeacher);
  }
  public void unenrollFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
    //from selected date
    //get the ClassSeat ID from the given attendance
    //remove Attendant ID if exist

    //check if emptied Class/ClassInstance
    log.info("unenroll fixed seat: {} attendent: {} is teacher: {}", seat, attendant, isTeacher);
  }

  public void enrollTemporary(ClassSeat seat, Attendant attendant, boolean isTeacher) {
    log.info("enroll temporary seat: {} attendent: {} is teacher: {}", seat, attendant, isTeacher);
  }
  public void unenrollTemporary(ClassSeat seat, Attendant attendant, boolean isTeacher) {
    log.info("unenroll fixed seat: {} attendent: {} is teacher: {}", seat, attendant, isTeacher);
  }

  public void suspendFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
    //Attendance->pupils[i]->fixed = ClassSeatSlotStatus.SUSPENDED
    log.info("enroll fixed seat: {} attendent: {} is teacher: {}", seat, attendant, isTeacher);
  }
  public void unsuspendFixed(ClassSeat seat, Attendant attendant, boolean isTeacher) {
    //Attendance->pupils[i]->fixed = ClassSeatSlotStatus.OCCUPIED
    log.info("unsuspend fixed seat: {} attendent: {} is teacher: {}", seat, attendant, isTeacher);
  }

  public int getEmptySeats(ClassInstance cl) {
    //for all seats in attendance, list those that are not in state EMPTY or SUSPENDED_EMPTY

    return 0;
  }

  public int getEmptyFixedSeats() {
    //for all seats in attendance, list those that are not in state EMPTY or SUSPENDED_EMPTY and is fixed

    return 0;
  }

  private AttendanceForm transfrom(Attendance attendance){
    return new AttendanceForm();
  }
}
