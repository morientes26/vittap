package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.core.studioadmin.mvvm.ClassCategoryViewModel;
import com.vitta_pilates.model.dao.ClassCategory;
import com.vitta_pilates.model.repository.ClassCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class ClassCategoryService extends EntityService<ClassCategory> {

  private final Logger log = LoggerFactory.getLogger(ClassCategoryService.class);

	@Autowired
	ClassCategoryRepository repository;

	public ClassCategoryService(JpaRepository repository) {
		super(repository);
	}

	public List<ClassCategory> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
