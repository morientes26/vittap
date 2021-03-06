package com.vitta_pilates.core.event.service;

import com.vitta_pilates.core.event.component.EventForm;
import com.vitta_pilates.core.event.component.Filter;
import com.vitta_pilates.core.event.component.SelectPersonResult;
import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.dao.Class;
import com.vitta_pilates.model.dao.attendance.Attendance;
import db.data.DataInitializer;
import com.vitta_pilates.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class EventService {

  private final Logger log = LoggerFactory.getLogger(EventService.class);

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  @Autowired
  ClassTemplateRepository classTemplateRepository;

  @Autowired
  ScheduleRepository scheduleRepository;

  @Autowired
  ClassRepository classRepository;

  @Autowired
  AttendantRepository attendantRepository;

  @Autowired
  AttendanceRepository attendanceRepository;

  public List<Event> getDefaultData(){
    return getFiltred(null);
  }

  public List<Event> getFiltred(Filter filter){
    List<ClassInstance> classInstances;
    if (filter!=null) {
      if (filter.getType().equals("Teacher")){
        classInstances = classInstanceRepository.findByConductingTeacher(
                filter.getValue()
        );
      } else {
        classInstances = classInstanceRepository.findByClassCategory(
                filter.getValue()
        );
      }
    } else {
      classInstances = classInstanceRepository.findAll();
    }
    return classInstances.stream().map(this::transform).collect(Collectors.toList());

  }

  public void save(EventForm eventForm){
    checkNotNull(eventForm);

    // update class instance
    ClassInstance classInstance = (!StringUtils.isEmpty(eventForm.getId()))
            ? classInstanceRepository.findOne(Long.valueOf(eventForm.getId()))
            : prepareNew(eventForm);

    ClassTemplate classTemplate = classTemplateRepository.getOne(eventForm.getTemplateType());

    // set data from modal
    // todo: event template class ????
    classInstance.setTrueTime(dateValue(eventForm.getStart()));
    classInstance.setName(eventForm.getName());
    classInstance.setDescription(eventForm.getDescription());
    classInstance.getClazz().setEvent(classTemplate);
    //todo: what about duration ??? is only on class not on instance
    classInstance.getClazz().getSchedule().setStartDate(dateValue(eventForm.getStart()));
    classInstance.getClazz().getSchedule().setEndDate(evaluateEndDate(
            dateValue(eventForm.getStart()), eventForm.getDuration()
    ));
    classInstance = classInstanceRepository.save(classInstance);

    // create empty attendance if type of class instance is CLASS and hasn't any attendence
    if (eventForm.getType().contains(EventForm.Type.CLASS.getName())){
      Attendance attendance = attendanceRepository.findOneByClassInstance(classInstance);
      if (attendance==null) {
        attendance = new Attendance();
        attendance.setClassInstance(classInstance);
        attendanceRepository.save(attendance);
      }
    }

    log.debug("Persist event id: {} from calendar event", classInstance.getId());
  }

  private ClassInstance prepareNew(EventForm eventForm){


    ClassTemplate classTemplate = classTemplateRepository.getOne(eventForm.getTemplateType());

    //todo: this create only custom template and class
    Schedule schedule = new Schedule(
            dateValue(eventForm.getStart()),
            evaluateEndDate(
                    dateValue(eventForm.getStart()), eventForm.getDuration()
            )
    );
    schedule = scheduleRepository.save(schedule);
    Class clazz = DataInitializer.instanceClass(schedule, classTemplate);
    clazz = classRepository.save(clazz);

    ClassInstance classInstance = new ClassInstance();
    classInstance.setClazz(clazz);
    return classInstance;
  }

  public void delete(String id){
    checkNotNull(id);
    ClassInstance classInstance = classInstanceRepository.findOne(Long.valueOf(id));
    Attendance attendance = attendanceRepository.findOneByClassInstance(classInstance);
    if (attendance!=null)
      attendanceRepository.delete(attendance);
    classInstanceRepository.deleteInBatch(Arrays.asList(classInstance));
    log.debug("Delete event id: {} from calendar event", id);
  }

  public Event get(String id){
    return transform(classInstanceRepository.getOne(Long.valueOf(id)), true);
  }

  public SelectPersonResult getPerson(String term, boolean isTeacher){
    if (term==null)
      term = "";
    SelectPersonResult result = new SelectPersonResult();
    List<Attendant> persons;
    if (isTeacher)
      persons = attendantRepository.findAllTeachersByName(term);
    else
      persons = attendantRepository.findAllPupilsByName(term);
    persons.forEach(item-> result.getResults().add(transform(item)));
    return result;
  }



  // private

  private SelectPersonResult.Person transform(Attendant attendant){
    return new SelectPersonResult.Person(String.valueOf(attendant.getId()), attendant.getPersonalData().getName());
  }

  private Event transform(ClassInstance classInstance){
    return transform(classInstance, false);
  }

  private Event transform(ClassInstance classInstance, boolean full){

    Attendance attendance = attendanceRepository.findOneByClassInstance(classInstance);
    int countOfPupil = 0;
    if (attendance!=null) {
      countOfPupil = pupilFilling(
              classInstance.getClazz().getEvent().getCapacity(),
              attendance.getPupils().size()
      );
    }

    Event event = new Event.EventBuilder()
            .setId(classInstance.getId())
            .setStart(classInstance.getTrueTime())
            .setEnd(classInstance.getClazz().getSchedule().getEndDate())
            .setDuration(classInstance.getClazz().getEvent().getDuration())
            .setTitle(
                    classInstance.getClazz().getEvent().getName() + " "
                  + classInstance.getClazz().getEvent().getRequiredLevel().getName()
            )
            .setColor(classInstance.getClazz().getEvent().getType().getColor())
            .setCount(countOfPupil)
            .setName(classInstance.getName())
            .setDescription(classInstance.getDescription())
            .createEvent();

    // option variable
    if (classInstance.getClazz().getConductingTeacher()!=null){
      event.setTeacher(classInstance.getClazz().getConductingTeacher().getPersonalData().getName());
    }
    if (classInstance.getClazz().getRoom()!=null){
      event.setRoom(classInstance.getClazz().getRoom().getName());
    }

    if (full){
      event.setType(EventForm.Type.SIMPLE.getName());
      if (attendance!=null) {
        event.setType(EventForm.Type.CLASS.getName());
      }
    }

    return event;
  }

  private Date dateValue(String dateS){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    try {
      return simpleDateFormat.parse(dateS);
    } catch (ParseException e) {
      log.error("Cannot convert string to date {}", dateS);
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Get empty seats of class instance
   * @param capacity - class capacity
   * @param countOfPupils - count of enrolled pupil
   * @return -1 if params are equal else real value of empty seats
   */
  private int pupilFilling(int capacity, int countOfPupils){
    if (countOfPupils==0)
      return -1;
    return capacity - countOfPupils;
  }

  private Date evaluateEndDate(Date start, int duration){
    if (start==null)
      return null;
    java.util.Calendar c = java.util.Calendar.getInstance();
    c.setTime(start);
    c.add(java.util.Calendar.MINUTE, duration);
    return c.getTime();
  }

}
