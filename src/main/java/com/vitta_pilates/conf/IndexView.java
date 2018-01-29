package com.vitta_pilates.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Initial web adapter for redirecting root site to login page
 */
@Configuration
public class IndexView extends WebMvcConfigurerAdapter {

  @Override
  public void addViewControllers( ViewControllerRegistry registry ) {
    registry.addViewController( "/" ).setViewName( "forward:/login.zul" );
    registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    super.addViewControllers( registry );
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //All resource URLs must have /static in root and the files must be in
    // static dir.
    registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
    super.addResourceHandlers(registry);
  }

}