package com.innovatrics.commons.vittap.repository;

import com.innovatrics.commons.vittap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by  ??_?� morientes on 30/12/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
