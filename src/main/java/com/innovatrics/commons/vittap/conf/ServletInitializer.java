package com.innovatrics.commons.vittap.conf;

import com.innovatrics.commons.vittap.Application;
import com.innovatrics.commons.vittap.auth.service.AuthenticationService;
import com.innovatrics.commons.vittap.auth.service.AuthenticationServiceImpl;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
