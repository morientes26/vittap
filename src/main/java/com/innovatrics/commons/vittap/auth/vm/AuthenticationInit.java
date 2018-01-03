package com.innovatrics.commons.vittap.auth.vm;

import com.innovatrics.commons.vittap.auth.service.AuthenticationService;
import com.innovatrics.commons.vittap.auth.service.AuthenticationServiceImpl;
import com.innovatrics.commons.vittap.auth.service.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import java.util.Map;

/**
 * Created by  ??_?¬ morientes on 02/01/2018.
 */
public class AuthenticationInit implements Initiator {

  //services
  AuthenticationService authService = new AuthenticationServiceImpl();

  public void doInit(Page page, Map<String, Object> args) throws Exception {

    UserCredential cre = authService.getUserCredential();
    if(cre==null || cre.isAnonymous()){
      Executions.sendRedirect("/login.zul");
    }
  }

}
