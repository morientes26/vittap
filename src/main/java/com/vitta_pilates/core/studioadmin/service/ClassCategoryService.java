package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.ClassCategory;
import com.vitta_pilates.model.repository.ClassCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClassCategoryService extends EntityService<ClassCategory> {

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
