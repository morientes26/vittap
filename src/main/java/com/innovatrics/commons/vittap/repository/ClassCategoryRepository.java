package com.innovatrics.commons.vittap.repository;

import com.innovatrics.commons.vittap.entity.ClassCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@Repository
public interface ClassCategoryRepository extends JpaRepository<ClassCategory, Long> {

}
