package com.innovatrics.commons.vittap.model.repository;

import com.innovatrics.commons.vittap.model.dao.ProgramTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProgramTemplateRepository extends JpaRepository<ProgramTemplate, Long> {

  @Query("Select r from ProgramTemplate r " +
          "where upper(r.name) LIKE upper(CONCAT('%',:keyword,'%')) " +
          "OR upper(r.description) LIKE upper(CONCAT('%',:keyword,'%'))")
  List<ProgramTemplate> findByNameOrDescription(@Param("keyword") String keyword);

}
