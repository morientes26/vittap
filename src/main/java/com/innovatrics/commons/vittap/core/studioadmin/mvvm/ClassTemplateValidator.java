package com.innovatrics.commons.vittap.core.studioadmin.mvvm;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

/**
 * Created by  ??_?¬ morientes on 08/01/2018.
 */
public class ClassTemplateValidator extends AbstractValidator{


  @Override
  public void validate(ValidationContext ctx) {
    addInvalidMessage(ctx, "value must not < 10 or > 100, but is " + ctx);
  }
}

