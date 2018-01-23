package com.vitta_pilates.core.people.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Attendant;
import com.vitta_pilates.model.dao.ClassInstance;
import com.vitta_pilates.model.repository.ClassInstanceRepository;
import com.vitta_pilates.model.repository.ClassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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

  public ClassService(JpaRepository repository) {
    super(repository);
  }

  @Transactional
  public ClassInstance executeInstance(ClassInstance instance, Attendant teacher, Attendant... pupils){
    log.debug("Execute instance {}", instance);
    instance.execute(teacher, pupils);
    return classInstanceRepository.save(instance);
  }
}
