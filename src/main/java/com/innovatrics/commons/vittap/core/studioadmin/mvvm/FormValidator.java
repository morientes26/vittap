package com.innovatrics.commons.vittap.core.studioadmin.mvvm;

import bsh.StringUtil;
import org.springframework.util.StringUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;


import java.util.Map;


public class FormValidator extends AbstractValidator{

  public void validate(ValidationContext ctx) {
    //all the bean properties
    Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

    validateEmpty(ctx, (String)beanProps.get("name").getValue(),"name");
    //validateEmpty(ctx, (String)beanProps.get("description").getValue(),"description");
  }

  private void validateEmpty(ValidationContext ctx, String value, String key) {
    if(StringUtils.isEmpty(value)) {
      this.addInvalidMessage(ctx, key, key.toUpperCase() + " cannot be null !");
    }
  }

}
