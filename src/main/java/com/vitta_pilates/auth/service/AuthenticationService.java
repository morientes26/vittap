package com.vitta_pilates.auth.service;

import org.springframework.stereotype.Service;

/**
 * Base authentication service interface
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