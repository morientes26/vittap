package com.innovatrics.commons.vittap;

import com.innovatrics.commons.vittap.auth.controller.LoginController;
import com.innovatrics.commons.vittap.conf.IndexView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class ApplicationTests {

	@Autowired
	private IndexView indexView;


	@Test
	public void contextLoads() {
		 assertNotNull(indexView);
	}

}
