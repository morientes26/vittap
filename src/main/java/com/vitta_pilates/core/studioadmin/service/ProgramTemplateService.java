package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.ProgramTemplate;
import com.vitta_pilates.model.repository.ProgramTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProgramTemplateService extends EntityService<ProgramTemplate>{

	private final Logger log = LoggerFactory.getLogger(ProgramTemplateService.class);

	@Autowired
	ProgramTemplateRepository repository;

	public ProgramTemplateService(JpaRepository repository) {
		super(repository);
	}

	public List<ProgramTemplate> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
