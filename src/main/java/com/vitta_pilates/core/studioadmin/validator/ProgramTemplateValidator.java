package com.vitta_pilates.core.studioadmin.validator;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;

import java.util.Map;


public class ProgramTemplateValidator extends FormValidator {

  public void validate(ValidationContext ctx) {
    //all the bean properties
    Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

    this.validateEmpty(ctx, (String)beanProps.get("name").getValue(),"name");
    //validateEmpty(ctx, (String)beanProps.get("description").getValue(),"description");
  }

}
