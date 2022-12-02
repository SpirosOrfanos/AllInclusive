package com.products.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValuesAllowed.Validator.class})
public @interface ValuesAllowed {

  String message() default "Field value should be greater than ";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String propName();

  double value() default 1d;

  class Validator implements ConstraintValidator<ValuesAllowed, Double> {
    private String propName;
    private String message;
    private Double allowable;

    @Override
    public void initialize(ValuesAllowed requiredIfChecked) {
      this.propName = requiredIfChecked.propName();
      this.message = requiredIfChecked.message();
      this.allowable = requiredIfChecked.value();
    }

    public boolean isValid(Double value, ConstraintValidatorContext context) {
      if (Objects.isNull(value)) {
        return true;
      }
      Boolean valid = value > this.allowable;

      if (!Boolean.TRUE.equals(valid)) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message.concat(this.allowable.toString()))
            .addPropertyNode(this.propName).addConstraintViolation();
      }
      return valid;
    }
  }
}
