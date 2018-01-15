package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.ClassCategory;
import com.vitta_pilates.model.dao.ClassInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@Repository
public interface ClassInstanceRepository extends JpaRepository<ClassInstance, Long> {


}
