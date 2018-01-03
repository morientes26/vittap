package com.innovatrics.commons.vittap.model;

import com.innovatrics.commons.vittap.Application;
import com.innovatrics.commons.vittap.model.dao.LevelOfAccess;
import com.innovatrics.commons.vittap.model.dao.User;
import com.innovatrics.commons.vittap.model.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
public class UserTest {

  private User user;

  @Autowired
  private UserRepository userRepository;

  @Before
  public void setUp() throws Exception {
    this.user = new User();
    this.user.setLevelOfAccess(LevelOfAccess.ADMIN);
  }

  @Test
  public void testUserCRUD() {

    User user = userRepository.save(this.user);
    User user2 = userRepository.findOne(user.getId());

    assertThat(user, instanceOf(User.class));
    assertThat(user2.getLevelOfAccess(), is(LevelOfAccess.ADMIN));

    userRepository.delete(user2);
    List<User> result = userRepository.findAll();

    assertThat(result.isEmpty(), is(true));

  }



}