package com.vitta_pilates.auth.controller;

import com.vitta_pilates.auth.service.AuthenticationService;
import com.vitta_pilates.auth.service.UserCredential;

import com.vitta_pilates.model.enumeration.LevelOfAccess;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
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

  public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo){
    ComponentInfo result = super.doBeforeCompose(page, parent, compInfo);
    authorization(authService.getUserCredential());
    return result;
  }

  @Listen("onClick=#login; onOK=#loginWin")
  public void doLogin(){
    String nm = account.getValue();
    String pd = password.getValue();

    if(!authService.login(nm,pd)){
      message.setValue("Account or password are not correct.");
      return;
    }
    UserCredential credential = authService.getUserCredential();
    message.setValue("Welcome, " + credential.getName());
    message.setSclass("");

    authorization(credential);

  }

  private void authorization(UserCredential credential){

    // anonymous is going to login page
    if(credential==null || credential.isAnonymous()){
      return;
    }

    if(credential.hasRole(LevelOfAccess.ADMIN.name())){
      Executions.sendRedirect("/index.zul");
      return;
    }

    if(credential.hasRole(LevelOfAccess.PUPIL.name())){
      Executions.sendRedirect("/index.zul");
      return;
    }

    if(credential.hasRole(LevelOfAccess.TEACHER.name())){
      Executions.sendRedirect("/index.zul");
      return;
    }

    if(credential.hasRole(LevelOfAccess.SECRETARY.name())){
      Executions.sendRedirect("/index.zul");
      return;
    }

    throw new IllegalStateException();
  }
}