package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Level;
import com.vitta_pilates.model.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService extends EntityService<Level> {

	@Autowired
	LevelRepository repository;

	public List<Level> getAll(){
		return repository.findAll();
	}

}
