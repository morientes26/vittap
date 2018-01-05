package com.innovatrics.commons.vittap.auth.controller;

import com.innovatrics.commons.vittap.auth.service.AuthenticationService;
import com.innovatrics.commons.vittap.auth.service.UserCredential;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;


@VariableResolver(DelegatingVariableResolver.class)
public class LoginController extends SelectorComposer<Component> {

  @Wire
  Textbox account;
  @Wire
  Textbox password;
  @Wire
  Label message;

  @WireVariable("authenticationServiceImpl")
  AuthenticationService authService;

  public void doAfterCompose(Component comp) throws Exception {
    super.doAfterCompose(comp);
    if (!authService.getUserCredential().isAnonymous())
      Executions.sendRedirect("/index.zul");
  }

  @Listen("onClick=#login; onOK=#loginWin")
  public void doLogin(){
    String nm = account.getValue();
    String pd = password.getValue();

    if(!authService.login(nm,pd)){
      message.setValue("Account or password are not correct.");
      return;
    }
    UserCredential cre= authService.getUserCredential();
    message.setValue("Welcome, "+cre.getName());
    message.setSclass("");

    Executions.sendRedirect("/index.zul");

  }
}