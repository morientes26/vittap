package com.vitta_pilates.model.repository;

import com.vitta_pilates.model.dao.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by  ??_?� morientes on 30/12/2017.
 */
@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

}
