package com.innovatrics.commons.vittap.core.studioadmin.service;

import com.innovatrics.commons.vittap.model.dao.ClassTemplate;
import com.innovatrics.commons.vittap.model.repository.ClassTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClassTemplateService {

	@Autowired
	ClassTemplateRepository repository;

	@Transactional
	public ClassTemplate save(ClassTemplate category){
		return repository.save(category);
	}

	@Transactional
	public void delete(ClassTemplate category){
		 repository.delete(category);
	}

	public ClassTemplate findOne(long id){ return repository.findOne(id); }

	public List<ClassTemplate> getAll(){
		return repository.findAll();
	}

	public List<ClassTemplate> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
