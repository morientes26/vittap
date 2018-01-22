package com.vitta_pilates.core.people.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.FilterData;
import com.vitta_pilates.model.dao.ProgramInstance;
import com.vitta_pilates.model.repository.AttendantRepository;
import com.vitta_pilates.model.repository.ClassInstanceRepository;
import com.vitta_pilates.model.repository.ProgramInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class PupilService extends EntityService<Attendant> {

  private final Logger log = LoggerFactory.getLogger(PupilService.class);

  @Autowired
  AttendantRepository repository;

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  @Autowired
  ProgramInstanceRepository programInstanceRepository;

  @Override
  @Transactional
  public Attendant save(Attendant entity) {
    log.debug("Save entity Attendant {}", entity);
    return repository.save(entity);
  }

  @Override
  public boolean delete(Attendant entity) {
    log.debug("Delete entity Attendant{}", entity);
    try {
      repository.delete(entity);
    } catch (Exception e) {
      log.warn("Cannot delete item {}", entity);
      return false;
    }
    return true;
  }

  @Override
  public Attendant findOne(long id) {
    return repository.findOne(id);
  }

  @Override
  public List<Attendant> getAll() {
    return repository.findAll();
  }

  @Override
  public List<Attendant> findByKeywords(String keyword) {
    return repository.findAllByName(keyword);
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
    entity.getPersonalData().setActive(!entity.getPersonalData().isActive());
    return entity;
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
          int month){

    LocalDate fromDate = LocalDate.now().plusMonths( month ).with(firstDayOfMonth());
    LocalDate toDate = LocalDate.now().plusMonths( month ).with(lastDayOfMonth());


    List<ClassInstance> result = classInstanceRepository.findByPupilAndDate(
            pupil.getId(),
            Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    );

    return result;

  }

  public List<ProgramInstance> findProgramInstanceByPupilAndPeriod(
          Attendant pupil,
          int month){

    LocalDate fromDate = LocalDate.now().plusMonths( month ).with(firstDayOfMonth());
    LocalDate toDate = LocalDate.now().plusMonths( month ).with(lastDayOfMonth());


    List<ProgramInstance> result = programInstanceRepository.findByPupilAndDate(
            pupil.getId(),
            Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    );

    return result;

  }

  public ClassInstance findClassInstanceByOne(Long id){
      return classInstanceRepository.findOne(id);
  }


}
