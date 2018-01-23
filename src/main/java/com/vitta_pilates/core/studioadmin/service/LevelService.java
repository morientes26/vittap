package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Level;
import com.vitta_pilates.model.repository.LevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class LevelService extends EntityService<Level> {

  private final Logger log = LoggerFactory.getLogger(LevelService.class);

	@Autowired
	LevelRepository repository;

  public LevelService(JpaRepository repository) {
    super(repository);
  }

}
