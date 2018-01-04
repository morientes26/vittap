package com.innovatrics.commons.vittap.auth.controller;

import com.innovatrics.commons.vittap.auth.service.AuthenticationService;
import com.innovatrics.commons.vittap.auth.service.AuthenticationServiceImpl;
import com.innovatrics.commons.vittap.auth.service.UserCredential;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.Map;

/**
 * Created by  ??_?¬ morientes on 02/01/2018.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class AuthenticationInit implements Initiator {

  @WireVariable("authenticationServiceImpl")
  AuthenticationService authService;

  public void doInit(Page page, Map<String, Object> args) throws Exception {

    //wire service manually by calling Selectors API
    Selectors.wireVariables(page, this, Selectors.newVariableResolvers(getClass(), null));

    UserCredential cre = authService.getUserCredential();
    if(cre==null || cre.isAnonymous()){
      Executions.sendRedirect("/login.zul");
    }
  }

}
