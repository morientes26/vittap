package com.vitta_pilates.core.people.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.repository.ClassInstanceRepository;
import com.vitta_pilates.model.repository.ClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClassService extends EntityService<com.vitta_pilates.model.dao.Class> {

  private final Logger log = LoggerFactory.getLogger(ClassService.class);

  @Autowired
  ClassRepository repository;

  @Autowired
  ClassInstanceRepository classInstanceRepository;

  @Transactional
  public com.vitta_pilates.model.dao.Class save(com.vitta_pilates.model.dao.Class entity) {
    log.debug("Save entity Class {}", entity);
    return repository.save(entity);
  }

  public boolean delete(com.vitta_pilates.model.dao.Class entity) {
    log.debug("Delete entity Class{}", entity);
    try {
      repository.delete(entity);
    } catch (Exception e) {
      log.warn("Cannot delete item {}", entity);
      return false;
    }
    return true;
  }

  @Transactional
  public ClassInstance executeInstance(ClassInstance instance, Attendant teacher, Attendant... pupils){
    log.debug("Execute instance {}", instance);
    instance.execute(teacher, pupils);
    return classInstanceRepository.save(instance);
  }

  public com.vitta_pilates.model.dao.Class findOne(long id) {
    return repository.findOne(id);
  }

  public List<com.vitta_pilates.model.dao.Class> getAll() {
    return repository.findAll();
  }

}
