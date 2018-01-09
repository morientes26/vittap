package com.innovatrics.commons.vittap.model.repository;

import com.innovatrics.commons.vittap.model.dao.Program;
import com.innovatrics.commons.vittap.model.dao.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {

}
