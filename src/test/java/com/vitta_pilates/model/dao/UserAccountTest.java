package com.vitta_pilates.model.dao;

import com.vitta_pilates.Application;
import com.vitta_pilates.model.enumeration.LevelOfAccess;
import com.vitta_pilates.model.repository.RoleRepository;
import com.vitta_pilates.model.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by  ??_?¬ morientes on 30/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class UserAccountTest {

  private UserAccount userAccount;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Before
  public void setUp() throws Exception {
    this.userAccount = new UserAccount();
    Role role = roleRepository.save(new Role(LevelOfAccess.ADMIN.name(),"test"));
    this.userAccount.setRole(role);
  }

  @Test
  public void testUserCRUD() {

    int count = userRepository.findAll().size();

    UserAccount userAccount = userRepository.save(this.userAccount);
    UserAccount userAccount2 = userRepository.findOne(userAccount.getId());

    assertThat(userAccount, instanceOf(UserAccount.class));
    assertThat(userAccount2.getRole().getName(), is(LevelOfAccess.ADMIN.name()));

    userRepository.delete(userAccount2);
    List<UserAccount> result = userRepository.findAll();

    assertThat(result.size(), is(count));

  }



}