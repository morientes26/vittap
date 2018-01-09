package com.innovatrics.commons.vittap.core.studioadmin.service;

import com.innovatrics.commons.vittap.model.dao.Level;
import com.innovatrics.commons.vittap.model.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {

	@Autowired
	LevelRepository repository;

	public List<Level> getAll(){
		return repository.findAll();
	}

}
