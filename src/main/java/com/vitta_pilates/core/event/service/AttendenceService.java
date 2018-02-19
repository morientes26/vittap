package com.vitta_pilates.core.event.service;

import com.vitta_pilates.core.event.component.ActionForm;
import com.vitta_pilates.core.event.component.AttendanceForm;
import com.vitta_pilates.core.event.component.AttendanceHelper;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.dao.attendance.ClassSeat;
import com.vitta_pilates.model.dao.attendance.ClassSeatSlot;
import com.vitta_pilates.model.enumeration.ClassSeatSlotStatus;
import com.vitta_pilates.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class AttendenceService {

  private final Logger log = LoggerFactory.getLogger(AttendenceService.class);

  @Autowired
  AttendanceRepository attendanceRepository;

  @Autowired
  AttendantRepository attendantRepository;

  @Autowired
  ClassSeatRepository classSeatRepository;

  @Autowired
  ClassSeatSlotRepository classSeatSlotRepository;

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  public ClassInstance getClassInstance(String attendanceId){
    Attendance attendance = attendanceRepository.findOne(Long.valueOf(attendanceId));
    return attendance.getClassInstance();
  }

  public List<AttendanceForm> getAttendance(String classInstanceId){
    if (classInstanceId==null)
      return new ArrayList<>();
    List<AttendanceForm> result = new ArrayList<>();
    ClassInstance classInstance = classInstanceRepository.findOne(Long.valueOf(classInstanceId));
    Attendance attendance = attendanceRepository.findOneByClassInstance(classInstance);
    if (attendance!=null){

      result.add(buildAttendanceForm(attendance, attendance.getTeacher()));

      attendance.getPupils().forEach(pupils -> result.add(buildAttendanceForm(attendance, pupils)));

      int emptySize = classInstance.getClazz().getEvent().getCapacity()-result.size();

      for (int i=0; i<emptySize;i++)
        result.add(buildAttendanceForm(attendance));

    }
    return result;
  }

  private AttendanceForm buildAttendanceForm(Attendance attendance){
    AttendanceForm result = new AttendanceForm();
    result.action = Collections.singletonList(AttendanceForm.Action.ENROLL);
    result.action2 = Collections.singletonList(AttendanceForm.Action.ENROLL);
    result.fixed =  AttendanceForm.Flag.EMPTY;
    result.temporary =  AttendanceForm.Flag.EMPTY;
    result.id = attendance.getId();
    return result;
  }

  private AttendanceForm buildAttendanceForm(Attendance attendance, ClassSeat classSeat){

    String name = "";
    AttendanceForm.Flag flagFixed = AttendanceForm.Flag.EMPTY;
    AttendanceForm.Flag flagTemporary = AttendanceForm.Flag.EMPTY;
    ClassSeatSlotStatus status = (classSeat!=null && classSeat.getFixed()!=null)
            ? classSeat.getFixed().getStatus() : ClassSeatSlotStatus.EMPTY;
    ClassSeatSlotStatus status2 = (classSeat!=null && classSeat.getTemporary()!=null)
            ? classSeat.getTemporary().getStatus() : ClassSeatSlotStatus.EMPTY;
    AttendanceHelper.ClassSeatStatus classSeatStatus = AttendanceHelper.getClassSeatStatus(status,status2);
    List<AttendanceForm.Action> action = Collections.singletonList(AttendanceForm.Action.ENROLL);
    List<AttendanceForm.Action> action2 = Collections.singletonList(AttendanceForm.Action.ENROLL);
    Long user = 0L;

    if (classSeat!=null)
      switch (classSeatStatus) {
        case EMPTY:
          action = Collections.singletonList(AttendanceForm.Action.ENROLL);
          action2 = Collections.singletonList(AttendanceForm.Action.ENROLL);
          break;
        case ENROLLED_FIXED:
          name = classSeat.getFixed().getAttendant().getPersonalData().getName();
          user = classSeat.getFixed().getAttendant().getId();
          flagFixed = AttendanceForm.Flag.ENROLL;
          flagTemporary = AttendanceForm.Flag.DISABLED;

          action = Arrays.asList(AttendanceForm.Action.UNENROLL, AttendanceForm.Action.SUSPEND);
          action2 = null;
          break;
        case ENROLLED_TEMPORARY:
          name = classSeat.getTemporary().getAttendant().getPersonalData().getName();
          user = classSeat.getTemporary().getAttendant().getId();
          flagFixed = AttendanceForm.Flag.DISABLED;
          flagTemporary = AttendanceForm.Flag.ENROLL;

          action = null;
          action2 = Collections.singletonList(AttendanceForm.Action.UNENROLL);
          break;
        case SUSPENDED_EMPTY:
          flagFixed = AttendanceForm.Flag.SUSPENDED;
          flagTemporary = AttendanceForm.Flag.EMPTY;
          user = classSeat.getFixed().getAttendant().getId();

          action = Collections.singletonList(AttendanceForm.Action.UNSUSPEND);
          action2 = Collections.singletonList(AttendanceForm.Action.ENROLL);
          break;
        case SUSPENDED_ENROLLED:
          name = classSeat.getTemporary().getAttendant().getPersonalData().getName();
          user = classSeat.getTemporary().getAttendant().getId();
          flagFixed = AttendanceForm.Flag.SUSPENDED;
          flagTemporary = AttendanceForm.Flag.ENROLL;

          action = null;
          action2 = Collections.singletonList(AttendanceForm.Action.UNENROLL);
          break;
      }

    return new AttendanceForm.AttendanceFormBuilder()
            .setId(attendance.getId())
            .setFixed(flagFixed)
            .setTemporary(flagTemporary)
            .setName(name)
            .setUser(user)
            .setAction(action)
            .setAction2(action2)
            .createAttendanceForm();

  }

  public void action(ActionForm form){
    log.info("action {}", form);
    checkNotNull(form);
    checkNotNull(form.getAction());
    checkNotNull(form.isTeacher());
    checkNotNull(form.isFixed());
    checkNotNull(form.getUser());

    Attendance attendance = attendanceRepository.findOne(Long.valueOf(form.getAttendanceId()));
    Attendant user = attendantRepository.findOne(form.getUser());

    switch (form.getAction()){
      case "enroll":enroll(form, attendance, user); break;
      case "unenroll":unenroll(form, attendance, user); break;
      case "suspend":suspend(attendance, user); break;
      case "unsuspend":unsuspend(attendance, user); break;
    }
  }

  private void enroll(ActionForm form, Attendance attendance, Attendant user){

    ClassSeatSlot slot = new ClassSeatSlot(user);
    slot = classSeatSlotRepository.save(slot);
    ClassSeat seat = new ClassSeat();
    seat.setTeacher(form.isTeacher());
    if (form.isFixed())
      seat.setFixed(slot);
    else
      seat.setTemporary(slot);
    seat = classSeatRepository.save(seat);
    attendance.getClassSeats().add(seat);
    attendance = attendanceRepository.save(attendance);
    log.info("Enroll attendant {} to attendance {}", user.getId(), attendance.getId());
  }
  private void unenroll(ActionForm form, Attendance attendance, Attendant user){
    ClassSeat seat;
    if (form.isFixed()) {
      seat = classSeatRepository.findByAttendanceAndAttendantFixed(attendance, user).get(0);

    } else {
      seat = classSeatRepository.findByAttendanceAndAttendantTemporary(attendance, user).get(0);
    }
    attendance.getClassSeats().remove(seat);
    attendanceRepository.save(attendance);
    classSeatRepository.delete(seat);
  }
  private void suspend(Attendance attendance, Attendant user){
    ClassSeat seat = classSeatRepository.findByAttendanceAndAttendantFixed(attendance, user).get(0);
    seat.getFixed().setStatus(ClassSeatSlotStatus.SUSPENDED);
    classSeatRepository.save(seat);
  }
  private void unsuspend(Attendance attendance, Attendant user){
    ClassSeat seat = classSeatRepository.findByAttendanceAndAttendantFixed(attendance, user).get(0);
    seat.getFixed().setStatus(ClassSeatSlotStatus.OCCUPIED);
    classSeatRepository.save(seat);
  }

}
