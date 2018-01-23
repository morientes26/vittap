package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.ClassTemplate;
import com.vitta_pilates.model.repository.ClassTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClassTemplateService extends EntityService<ClassTemplate> {

	private final Logger log = LoggerFactory.getLogger(ClassCategoryService.class);

	@Autowired
	ClassTemplateRepository repository;

	public ClassTemplateService(JpaRepository repository) {
		super(repository);
	}

	public List<ClassTemplate> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
