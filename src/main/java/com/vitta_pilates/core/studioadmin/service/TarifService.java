package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.EntityService;
import com.vitta_pilates.model.dao.Tarif;
import com.vitta_pilates.model.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TarifService implements EntityService<Tarif> {

	@Autowired
	TarifRepository repository;

	@Override
	public Tarif save(Tarif tarif){
		return repository.save(tarif);
	}

	@Override
	public void delete(Tarif tarif){
		 repository.delete(tarif);
	}

	@Override
	public Tarif findOne(long id){ return repository.findOne(id); }

	@Override
	public List<Tarif> getAll(){
		return repository.findAll();
	}

	@Override
	public List<Tarif> findByKeywords(String keyword) {
		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);
	}

}
