package com.vitta_pilates.core.event.service;

import com.vitta_pilates.core.event.component.AttendanceForm;
import com.vitta_pilates.core.event.component.EventForm;
import com.vitta_pilates.core.event.component.Filter;
import com.vitta_pilates.core.event.component.SelectPersonResult;
import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.dao.Class;
import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.init.Initiator;
import com.vitta_pilates.model.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    ClassInstance classInstance;
    // update class instance
    if (!StringUtils.isEmpty(eventForm.getId())){
      classInstance = classInstanceRepository.findOne(Long.valueOf(eventForm.getId()));
    } else {
      classInstance = prepareNew(eventForm);
    }

    // set data from modal
    // todo: event template class ????
    classInstance.setTrueTime(dateValue(eventForm.getStart()));
    classInstance.setName(eventForm.getName());
    classInstance.setDescription(eventForm.getDescription());
    //todo: what about duration ??? is only on class not on instance
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

    ClassInstance classInstance = new ClassInstance();
    //todo: what kind of class template
    ClassTemplate classTemplate = classTemplateRepository.getOne(1L);

    //todo: this is only create custom template and class
    Schedule schedule = new Schedule(
            dateValue(eventForm.getStart()),
            evaluateEndDate(dateValue(eventForm.getStart()), eventForm.getDuration()
            )
    );
    schedule = scheduleRepository.save(schedule);
    Class clazz = Initiator.instanceClass(schedule, classTemplate);
    clazz = classRepository.save(clazz);

    classInstance.setClazz(clazz);
    return classInstance;
  }

  public void delete(String id){
    checkNotNull(id);
    ClassInstance classInstance = classInstanceRepository.findOne(Long.valueOf(id));
    attendanceRepository.delete(attendanceRepository.findOneByClassInstance(classInstance));
    classInstanceRepository.deleteInBatch(Arrays.asList(classInstance));
    log.debug("Delete event id: {} from calendar event", id);
  }

  public EventForm getEventForm(String id){
    log.debug("Load event id: {} from DB", id);
    ClassInstance classInstance = classInstanceRepository.findOne(Long.valueOf(id));
    Attendance attendance = attendanceRepository.findOneByClassInstance(classInstance);
    return transform(classInstance, attendance);
  }

  public Event get(String id){
    return transform(classInstanceRepository.getOne(Long.valueOf(id)));
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
    Event event = new Event.EventBuilder()
            .setId(classInstance.getId())
            .setStart(classInstance.getTrueTime())
            .setEnd(evaluateEndDate(classInstance.getTrueTime(), classInstance.getClazz().getEvent().getDuration()))
            .setDuration(classInstance.getClazz().getEvent().getDuration())
            .setTitle(
                    classInstance.getClazz().getEvent().getName() + " "
                  + classInstance.getClazz().getEvent().getRequiredLevel().getName()
            )
            .setColor(classInstance.getClazz().getEvent().getType().getColor())
            .setCount(pupilFilling(
                    classInstance.getClazz().getEvent().getCapacity(),
                    classInstance.getAttendedPupils().size()))
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

    return event;
  }

  private EventForm transform(ClassInstance classInstance, Attendance attendance){
    EventForm eventForm = new EventForm();
    eventForm.setId(String.valueOf(classInstance.getId()));
    eventForm.setName(classInstance.getName());
    eventForm.setDescription(classInstance.getDescription());
   //todo: eventForm.setDuration(classInstance.get);
   //todo: eventForm.setOccurence();
   //todo: eventForm.setRecurrentType();
    eventForm.setStart(classInstance.getTrueTime().toString());
    eventForm.setDuration(classInstance.getClazz().getEvent().getDuration());
    eventForm.setTitle(
            classInstance.getClazz().getEvent().getName() + " "
                    + classInstance.getClazz().getEvent().getRequiredLevel().getName());
    //todo:eventForm.setType();
    //todo:
    eventForm.setAttendanceTeacherForm(null);
    eventForm.setAttendanceForm(null);

    return eventForm;
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
