package com.vitta_pilates.core.shared.validator;

import org.springframework.util.StringUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;


public abstract class FormValidator extends AbstractValidator{

  abstract public void validate(ValidationContext ctx);

  public void validateEmpty(ValidationContext ctx, String value, String key) {
    if(StringUtils.isEmpty(value)) {
      this.addInvalidMessage(ctx, key, key.toUpperCase() + " cannot be null !");
    }
  }

  public void validateInteger(ValidationContext ctx, String value, String key) {
    if (value==null)
      return;
    try {
      Integer test = Integer.parseInt(value);
    } catch (NumberFormatException e){
      this.addInvalidMessage(ctx, key, key.toUpperCase() + " bad format of integer !");
    }

  }

}
