package com.vitta_pilates.core.people.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.dao.ProgramInstance;
import com.vitta_pilates.model.dao.attendance.Attendance;
import com.vitta_pilates.model.enumeration.FilterData;
import com.vitta_pilates.model.repository.AttendanceRepository;
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
import java.util.ArrayList;
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
  AttendanceRepository attendanceRepository;

  @Autowired
  ProgramInstanceRepository programInstanceRepository;

  public PupilService(AttendantRepository repository){
    super(repository);
  }

  @Transactional
  public Attendant register(Attendant attendant){
    log.debug("Register pupil {}", attendant);
    attendant.setPupil(true);
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

      case ALL: return repository.findAllPupilsByName(keyword);

      case NON_ENROLLED: return repository.findNonEnrolled();

      case OPEN_DEBTS: return repository.findOpenDepts();
    }

    return repository.findAllPupilsByName(keyword);
  }

  public List<ClassInstance> findClassInstanceByPupilAndPeriod(
          Attendant pupil,
          int month){

    LocalDate fromDate = LocalDate.now().plusMonths( month ).with(firstDayOfMonth());
    LocalDate toDate = LocalDate.now().plusMonths( month ).with(lastDayOfMonth());

    List<Attendance> attendances = attendanceRepository.findByPupilAndDate(
            pupil.getId(),
            Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
            Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    );
    List<ClassInstance> result = new ArrayList<>();
    attendances.forEach(att-> result.add(att.getClassInstance()));

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
