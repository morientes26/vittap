package com.vitta_pilates.core.people.validator;

import com.vitta_pilates.core.shared.validator.FormValidator;
import com.vitta_pilates.model.dao.Attendant;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;

import java.util.Map;

public class PupilValidator extends FormValidator{

  @Override
  public void validate(ValidationContext ctx) {
    //all the bean properties
    Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
    //this.validateEmpty(ctx, (String)beanProps.get("personalData.name").getValue(),"personalData.name");
  }
}

