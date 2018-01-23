package com.vitta_pilates.core.people.validator;

import com.vitta_pilates.core.shared.validator.FormValidator;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;

import java.util.Map;

public class TeacherValidator extends FormValidator{

  @Override
  public void validate(ValidationContext ctx) {

    Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

  }
}

