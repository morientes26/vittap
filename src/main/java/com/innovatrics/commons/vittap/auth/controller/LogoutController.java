package com.innovatrics.commons.vittap.auth.controller;

import com.innovatrics.commons.vittap.auth.service.AuthenticationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;


@VariableResolver(DelegatingVariableResolver.class)
public class LogoutController extends SelectorComposer<Component> {

  @WireVariable("authenticationServiceImpl")
  AuthenticationService authService;
//
//  @Listen("onClick=#logout")
//  public void doLogout(){
//    authService.logout();
//    Executions.sendRedirect("/login.zul");
//  }

  public void doAfterCompose(Component comp) throws Exception {
    super.doAfterCompose(comp);
    authService.logout();
    Executions.sendRedirect("/login.zul");
  }
}