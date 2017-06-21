package com.shhetri.validators;

import com.shhetri.validators.annotations.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone phone) {
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNo.matches("\\d{10}") || phoneNo.matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}") || phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}") || phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
    }
}
