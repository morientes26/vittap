package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.ProgramTemplate;
import com.vitta_pilates.model.repository.ProgramTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProgramTemplateService extends EntityService<ProgramTemplate>{

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
