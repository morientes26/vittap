package com.innovatrics.commons.vittap.model.repository;

import com.innovatrics.commons.vittap.model.dao.ClassTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@Repository
public interface ClassTemplateRepository extends JpaRepository<ClassTemplate, Long> {

  @Query("Select r from ClassTemplate r " +
          "where upper(r.name) LIKE upper(CONCAT('%',:keyword,'%')) " +
          "OR upper(r.description) LIKE upper(CONCAT('%',:keyword,'%'))")
  List<ClassTemplate> findByNameOrDescription(@Param("keyword") String keyword);
}
