package com.innovatrics.commons.vittap.auth.service;

import com.innovatrics.commons.vittap.auth.service.UserCredential;
import org.springframework.stereotype.Service;

/**
 * Created by  ??_?¬ morientes on 02/01/2018.
 */
@Service
public interface AuthenticationService {

  /**login with account and password**/
  public boolean login(String account, String password);

  /**logout current user**/
  public void logout();

  /**get current user credential**/
  public UserCredential getUserCredential();

}