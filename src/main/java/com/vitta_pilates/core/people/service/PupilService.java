package com.vitta_pilates.core.people.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.FilterData;
import com.vitta_pilates.model.repository.AttendantRepository;
import com.vitta_pilates.model.repository.ClassInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class PupilService extends EntityService<Attendant> {

  private final Logger log = LoggerFactory.getLogger(PupilService.class);

  @Autowired
  AttendantRepository repository;

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  @Transactional
  public Attendant save(Attendant entity) {
    log.debug("Save entity Attendant {}", entity);
    return repository.save(entity);
  }

  @Transactional
  public void delete(Attendant entity) {
    log.debug("Delete entity Attendant{}", entity);
    repository.delete(entity);
  }

  @Transactional
  public Attendant register(Attendant attendant){
    log.debug("Register attendent {}", attendant);
    return repository.save(attendant);
  }

  @Transactional
  public Attendant changeStatus(Attendant entity) {
    log.debug("Change status entity Attendant {}", entity);
    Attendant attendant = repository.findOne(entity.getId());
    attendant.getPersonalData().setActive(!entity.getPersonalData().isActive());
    repository.save(attendant);
    entity.getPersonalData().setActive(attendant.getPersonalData().isActive());
    return entity;
  }

  public Attendant findOne(long id) {
    return repository.findOne(id);
  }

  public List<Attendant> getAll() {
    return repository.findAll();
  }

  public List<Attendant> findByKeywords(String keyword) {
    return repository.findAllByName(keyword);
  }

  public List<Attendant> findByFilterAndKeywords(FilterData filterData, String keyword) {

    checkNotNull(filterData);

    switch (filterData) {

      case ALL: return repository.findAllByName(keyword);

      case NON_ENROLLED: return repository.findNonEnrolled();

      case OPEN_DEBTS: return repository.findOpenDepts();
    }

    return repository.findAllByName(keyword);
  }

  public List<ClassInstance> findClassInstanceByPupilAndPeriod(
          Attendant pupil,
          int mounth){

    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.MONTH, mounth);

    return classInstanceRepository.findByPupilAndDate(
            pupil.getId(),
            c.getTime(),
            new Date()
    );

  }

  public ClassInstance findClassInstanceByOne(Long id){
      return classInstanceRepository.findOne(id);
  }


}
