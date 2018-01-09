package com.innovatrics.commons.vittap.core.studioadmin.service;

import com.innovatrics.commons.vittap.model.dao.ProgramTemplate;
import com.innovatrics.commons.vittap.model.repository.ProgramTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProgramTemplateService {

	@Autowired
	ProgramTemplateRepository repository;

	@Transactional
	public ProgramTemplate save(ProgramTemplate programTemplate){
		return repository.save(programTemplate);
	}

	@Transactional
	public void delete(ProgramTemplate programTemplate){
		 repository.delete(programTemplate);
	}

	public ProgramTemplate findOne(long id){ return repository.findOne(id); }

	public List<ProgramTemplate> getAll(){
		return repository.findAll();
	}

	public List<ProgramTemplate> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
