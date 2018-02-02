package com.vitta_pilates.core.event.service;

import com.vitta_pilates.core.event.component.Filter;
import com.vitta_pilates.model.dao.Class;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.Event;
import com.vitta_pilates.model.init.Initiator;
import com.vitta_pilates.model.repository.AttendantRepository;
import com.vitta_pilates.model.repository.ClassInstanceRepository;
import com.vitta_pilates.model.repository.ClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class EventService {

  private final Logger log = LoggerFactory.getLogger(EventService.class);

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  @Autowired
  ClassRepository classRepository;

  @Autowired
  AttendantRepository attendantRepository;

  public List<Event> getDefaultData(){
    return getFiltred(null);
  }

  public List<Event> getFiltred(Filter filter){
    List<ClassInstance> classInstances;
    if (filter!=null) {
      if (filter.getType().equals("Teacher")){
        classInstances = classInstanceRepository.findByTeacher(
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
    List<Event> result = new ArrayList<>();

    for (ClassInstance classInstance : classInstances){
      Event event = new Event.EventBuilder()
              .setId(classInstance.getId())
              .setStart(classInstance.getTrueTime())
              .setEnd(classInstance.getTrueTime(), classInstance.getClazz().getEvent().getDuration())
              .setTitle(classInstance.getClazz().getEvent().getName())
              .setColor(classInstance.getClazz().getEvent().getType().getColor())
              .setCount(pupilFilling(
                      classInstance.getClazz().getEvent().getCapacity(),
                      classInstance.getAttendedPupils().size()))
              .createEvent();

      // option variable
      if (classInstance.getClazz().getConductingTeacher()!=null){
        event.setTeacher(classInstance.getClazz().getConductingTeacher().getPersonalData().getName());
      }
      result.add(event);
    }

    return result;
  }

  public void save(Event event){
    checkNotNull(event);
    List<Class> clazzies = classRepository.findAll();
    int randomNum = ThreadLocalRandom.current().nextInt(0, clazzies.size());
    ClassInstance classInstance = Initiator.generateClassInstance(clazzies.get(randomNum));
    classInstance.setTrueTime(event.getStart());
    classInstance = classInstanceRepository.save(classInstance);
    log.debug("Add event id: {} from calendar event", classInstance.getId());
  }

  public void delete(String id){
    checkNotNull(id);
    ClassInstance classInstance = classInstanceRepository.findOne(Long.valueOf(id));
    classInstanceRepository.deleteInBatch(Arrays.asList(classInstance));
    log.debug("Delete event id: {} from calendar event", id);
  }

  /**
   * Get empty seats of class instance
   * @param capacity - class capacity
   * @param countOfPupils - count of enrolled pupil
   * @return -1 if params are equal else real value of empty seats
   */
  private int pupilFilling(int capacity, int countOfPupils){
    if (capacity==countOfPupils)
      return -1;
    return capacity - countOfPupils;
  }

}
