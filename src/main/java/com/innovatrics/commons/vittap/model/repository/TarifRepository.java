package com.innovatrics.commons.vittap.model.repository;

import com.innovatrics.commons.vittap.model.dao.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {

  @Query("Select r from Tarif r " +
          "where upper(r.name) LIKE upper(CONCAT('%',:keyword,'%')) " +
          "OR upper(r.description) LIKE upper(CONCAT('%',:keyword,'%'))")
  List<Tarif> findByNameOrDescription(@Param("keyword") String keyword);

}
