package com.shhetri.validators;

import com.shhetri.validators.annotations.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {
    private final HttpServletRequest request;

    @Autowired
    public PasswordValidator(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void initialize(Password password) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String id = pathVariables.get("id");

        return id != null || (password != null && !password.trim().isEmpty());
    }
}
