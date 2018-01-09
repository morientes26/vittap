package com.innovatrics.commons.vittap.core.studioadmin.validator;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;

import java.util.Map;

public class DefaultValidator extends FormValidator{

  @Override
  public void validate(ValidationContext ctx) {
    //all the bean properties
    Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

    this.validateEmpty(ctx, (String)beanProps.get("name").getValue(),"name");
  }
}

