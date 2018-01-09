package com.innovatrics.commons.vittap.core.studioadmin.service;

import com.innovatrics.commons.vittap.model.dao.ClassCategory;
import com.innovatrics.commons.vittap.model.repository.ClassCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClassCategoryService {

	@Autowired
	ClassCategoryRepository repository;

	@Transactional
	public ClassCategory save(ClassCategory category){
		return repository.save(category);
	}

	@Transactional
	public void delete(ClassCategory category){
		 repository.delete(category);
	}

	public ClassCategory findOne(long id){ return repository.findOne(id); }

	public List<ClassCategory> getAll(){
		return repository.findAll();
	}

	public List<ClassCategory> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
