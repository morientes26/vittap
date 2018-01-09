package com.innovatrics.commons.vittap.model.repository;

import com.innovatrics.commons.vittap.model.dao.Level;
import com.innovatrics.commons.vittap.model.dao.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

}
