package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Level;
import com.vitta_pilates.model.repository.LevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
public class LevelService extends EntityService<Level> {

  private final Logger log = LoggerFactory.getLogger(LevelService.class);

	@Autowired
	LevelRepository repository;

  @Override
  public Level save(Level entity) {
    return repository.save(entity);
  }

  @Override
  public boolean delete(Level entity) {
    try {
      repository.delete(entity);
    } catch (Exception e) {
      log.warn("Cannot delete item {}", entity);
      return false;
    }
    return true;
  }

  @Override
  public Level findOne(long id) {
    return repository.findOne(id);
  }

  @Override
	public List<Level> getAll(){
		return repository.findAll();
	}

  @Override
  public List<Level> findByKeywords(String keyword) {
	  throw new NotImplementedException();
  }

}
