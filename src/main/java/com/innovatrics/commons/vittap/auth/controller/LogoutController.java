package com.innovatrics.commons.vittap.auth.controller;

import com.innovatrics.commons.vittap.auth.service.AuthenticationService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

/**
 * LogoutController without template.
 * After redirect site to this controller, user will be logout and
 * redirect to login page.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class LogoutController extends SelectorComposer<Component> {

  @WireVariable("authenticationServiceImpl")
  AuthenticationService authService;

  public void doAfterCompose(Component comp) throws Exception {
    super.doAfterCompose(comp);
    authService.logout();
    Executions.sendRedirect("/login.zul");
  }
}