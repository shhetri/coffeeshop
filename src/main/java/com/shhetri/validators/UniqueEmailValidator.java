package com.shhetri.validators;

import com.shhetri.repository.PersonRepository;
import com.shhetri.validators.annotations.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final PersonRepository personRepository;
    private final HttpServletRequest request;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UniqueEmailValidator(PersonRepository personRepository, HttpServletRequest request) {
        this.personRepository = personRepository;
        this.request = request;
    }

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String id = pathVariables.get("id");

        if (id != null) {
            return !personRepository.findFirstByEmailAndIdNot(email, Integer.parseInt(id)).isPresent();
        }

        return !personRepository.findFirstByEmail(email).isPresent();
    }
}
