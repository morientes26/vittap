package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Tarif;
import com.vitta_pilates.model.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifService extends EntityService<Tarif> {

	@Autowired
	TarifRepository repository;

  TarifService(){}

	public Tarif save(Tarif tarif){
		return repository.save(tarif);
	}

	public void delete(Tarif tarif){
		 repository.delete(tarif);
	}

	public Tarif findOne(long id){ return repository.findOne(id); }

	public List<Tarif> getAll(){
		return repository.findAll();
	}

	public List<Tarif> findByKeywords(String keyword) {
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}

}
