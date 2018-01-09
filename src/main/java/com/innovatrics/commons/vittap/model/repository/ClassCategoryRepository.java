package com.innovatrics.commons.vittap.model.repository;

import com.innovatrics.commons.vittap.model.dao.ClassCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by  ??_?� morientes on 30/12/2017.
 */
@Repository
public interface ClassCategoryRepository extends JpaRepository<ClassCategory, Long> {

  @Query("Select r from ClassCategory r " +
          "where upper(r.name) LIKE upper(CONCAT('%',:keyword,'%')) " +
          "OR upper(r.description) LIKE upper(CONCAT('%',:keyword,'%'))")
  List<ClassCategory> findByNameOrDescription(@Param("keyword") String keyword);

}
