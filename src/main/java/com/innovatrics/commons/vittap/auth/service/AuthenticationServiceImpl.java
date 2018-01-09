package com.innovatrics.commons.vittap.auth.service;

import com.innovatrics.commons.vittap.model.dao.User;
import com.innovatrics.commons.vittap.model.repository.UserRepository;
import com.sun.tools.classfile.Opcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

/**
 * Implementation of basic user authentication.
 * User will get userCredencial after login.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

  @Autowired
  UserRepository userRepository;

  @Override
  public boolean login(String nm, String pd) {
    User user = userRepository.findOneByLogin(nm);
    //a simple plan text password verification
    if(user==null || !user.getPassword().equals(pd)){
      return false;
    }

    Session sess = Sessions.getCurrent();
    UserCredential cre = new UserCredential(
            user.getLogin(),
            user.getName(),
            Arrays.asList(user.getRole().getName())
    );

    //just in case for this demo.
    if(cre.isAnonymous()){
      return false;
    }
    sess.setAttribute("userCredential",cre);

    return true;
  }

  @Override
  public void logout() {
    Session sess = Sessions.getCurrent();
    sess.removeAttribute("userCredential");
  }

  public UserCredential getUserCredential(){
    Session sess = Sessions.getCurrent();
    UserCredential cre = (UserCredential)sess.getAttribute("userCredential");
    if(cre==null){
      cre = new UserCredential();//new a anonymous user and set to session
      sess.setAttribute("userCredential",cre);
    }
    return cre;
  }

}