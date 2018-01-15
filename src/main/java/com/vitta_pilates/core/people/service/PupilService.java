package com.vitta_pilates.core.people.service;

import com.vitta_pilates.core.shared.EntityService;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.repository.AttendantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PupilService implements EntityService<Attendant> {

  private final Logger log = LoggerFactory.getLogger(PupilService.class);

  @Autowired
  AttendantRepository repository;

  @Override
  public Attendant save(Attendant entity) {
    log.debug("Save entity Attendant {}", entity);
    return repository.save(entity);
  }

  @Override
  public void delete(Attendant entity) {
    log.debug("Delete entity Attendant{}", entity);
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
    return repository.findByName(keyword);
  }
}
