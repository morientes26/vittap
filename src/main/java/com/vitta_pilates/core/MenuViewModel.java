package com.vitta_pilates.core;

import com.vitta_pilates.auth.service.AuthenticationService;
import com.vitta_pilates.auth.service.UserCredential;
import com.vitta_pilates.model.enumeration.LevelOfAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class MenuViewModel {

  private final Logger log = LoggerFactory.getLogger(MenuViewModel.class);

  private boolean admin = false;
  private boolean pupil = false;
  private boolean teacher = false;
  private boolean secretary = false;

  @WireVariable("authenticationServiceImpl")
  AuthenticationService authService;

  @Init
  public void init(){

    UserCredential credential = authService.getUserCredential();
    admin = credential.hasRole(LevelOfAccess.ADMIN.name());
    pupil = credential.hasRole(LevelOfAccess.PUPIL.name());
    teacher = credential.hasRole(LevelOfAccess.TEACHER.name());
    secretary = credential.hasRole(LevelOfAccess.SECRETARY.name());
    log.debug("Load menu for {}", credential.getAccount());
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public boolean isPupil() {
    return pupil;
  }

  public void setPupil(boolean pupil) {
    this.pupil = pupil;
  }

  public boolean isTeacher() {
    return teacher;
  }

  public void setTeacher(boolean teacher) {
    this.teacher = teacher;
  }

  public boolean isSecretary() {
    return secretary;
  }

  public void setSecretary(boolean secretary) {
    this.secretary = secretary;
  }
}
