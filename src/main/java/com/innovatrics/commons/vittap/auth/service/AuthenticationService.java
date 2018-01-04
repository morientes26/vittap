package com.innovatrics.commons.vittap.auth.service;

import org.springframework.stereotype.Service;

/**
 * Created by  ??_?¬ morientes on 02/01/2018.
 */
@Service
public interface AuthenticationService {

  /**login with account and password**/
  boolean login(String account, String password);

  /**logout current user**/
  void logout();

  /**get current user credential**/
  UserCredential getUserCredential();

}