package com.vitta_pilates.auth.service;

import com.vitta_pilates.model.dao.UserAccount;
import com.vitta_pilates.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Implementation of basic user authentication.
 * UserAccount will get userCredencial after login.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {

  @Autowired
  UserRepository userRepository;

  @Override
  public boolean login(String nm, String pd) {
    UserAccount userAccount = userRepository.findOneByLogin(nm);
    //a simple plan text password verification
    if(userAccount ==null || !userAccount.getPassword().equals(pd)){
      return false;
    }

    Session sess = Sessions.getCurrent();
    UserCredential cre = new UserCredential(
            userAccount.getLogin(),
            userAccount.getName(),
            Arrays.asList(userAccount.getRole().getName())
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