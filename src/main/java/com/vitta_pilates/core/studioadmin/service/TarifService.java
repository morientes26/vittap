package com.vitta_pilates.core.studioadmin.service;

import com.vitta_pilates.core.shared.service.EntityService;
import com.vitta_pilates.model.dao.Tarif;
import com.vitta_pilates.model.repository.TarifRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TarifService extends EntityService<Tarif> {

	private final Logger log = LoggerFactory.getLogger(TarifService.class);

	@Autowired
	TarifRepository repository;

  public TarifService(JpaRepository repository){
		super(repository);
	}

	public List<Tarif> findByKeywords(String keyword){

		keyword = (keyword==null) ? "*" : keyword;
		return repository.findByNameOrDescription(keyword);

	}

}
