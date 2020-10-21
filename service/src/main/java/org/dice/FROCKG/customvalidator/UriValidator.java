package org.dice.FROCKG.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.dice.FROCKG.utilities.Validator;

public class UriValidator implements ConstraintValidator<IUriConstraint, String> {

  @Override
  public void initialize(IUriConstraint uri) {}

  @Override
  public boolean isValid(String uri, ConstraintValidatorContext context) {
    return Validator.IsUriValid(uri);
  }
}
