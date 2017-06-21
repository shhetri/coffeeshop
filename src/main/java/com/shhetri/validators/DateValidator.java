package com.shhetri.validators;

import com.shhetri.validators.annotations.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator implements ConstraintValidator<Date, String> {
    private Date dateAnnotation;

    @Override
    public void initialize(Date dateAnnotation) {
        this.dateAnnotation = dateAnnotation;
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateAnnotation.pattern());
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
