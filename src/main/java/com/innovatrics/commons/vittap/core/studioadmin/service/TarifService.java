package com.innovatrics.commons.vittap.core.studioadmin.service;

import com.innovatrics.commons.vittap.model.dao.Tarif;
import com.innovatrics.commons.vittap.model.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TarifService {

	@Autowired
	TarifRepository repository;

	@Transactional
	public Tarif save(Tarif tarif){
		return repository.save(tarif);
	}

	@Transactional
	public void delete(Tarif tarif){
		 repository.delete(tarif);
	}

	public Tarif findOne(long id){ return repository.findOne(id); }

	public List<Tarif> getAll(){
		return repository.findAll();
	}

	public List<Tarif> findByNameOrDescription(String keyword){
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}
}
