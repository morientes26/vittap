package com.vitta_pilates.core.people.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.*;
import com.vitta_pilates.model.enumeration.FilterTeacherData;
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
public class TeacherService extends EntityService<Attendant> {

  private final Logger log = LoggerFactory.getLogger(TeacherService.class);

  @Autowired
  AttendantRepository repository;

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  @Autowired
  ProgramInstanceRepository programInstanceRepository;

  public TeacherService(AttendantRepository repository){
    super(repository);
  }

  @Transactional
  public Attendant register(Attendant attendant){
    log.debug("Register tacher {}", attendant);
    attendant.setPupil(false);
    return repository.save(attendant);
  }

  //TODO: merging with pupilService
  @Transactional
  public Attendant changeStatus(Attendant entity) {
    log.debug("Change status entity Attendant {}", entity);
    Attendant attendant = repository.findOne(entity.getId());
    attendant.getPersonalData().setActive(!entity.getPersonalData().isActive());
    repository.save(attendant);
    entity.getPersonalData().setActive(!entity.getPersonalData().isActive());
    return entity;
  }

  public List<Attendant> findByFilterAndKeywords(FilterTeacherData filterData, String keyword) {

    checkNotNull(filterData);

    switch (filterData) {

      case ALL: return repository.findAllTeachersByName(keyword);

      case PENDING_SALARIES: return repository.findPendingSalaries();

    }

    return repository.findAllTeachersByName(keyword);
  }

  public List<ClassInstance> findClassInstanceByPeriod(
          Attendant pupil,
          int month){

    LocalDate fromDate = LocalDate.now().plusMonths( month ).with(firstDayOfMonth());
    LocalDate toDate = LocalDate.now().plusMonths( month ).with(lastDayOfMonth());


    List<ClassInstance> result = classInstanceRepository.findByTeacherAndDate(
            pupil.getId(),
            Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    );

    return result;

  }

  public List<ProgramInstance> findProgramInstanceByPeriod(
          Attendant pupil,
          int month) {

    LocalDate fromDate = LocalDate.now().plusMonths(month).with(firstDayOfMonth());
    LocalDate toDate = LocalDate.now().plusMonths(month).with(lastDayOfMonth());


    List<ProgramInstance> result = programInstanceRepository.findByTeacherAndDate(
            pupil.getId(),
            Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    );

    return result;
  }

}
