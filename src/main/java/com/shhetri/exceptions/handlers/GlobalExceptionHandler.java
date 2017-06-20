package com.shhetri.exceptions.handlers;

import com.shhetri.exceptions.ModelNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ModelNotFoundException e, WebRequest request) {
        AbstractMap.SimpleEntry<String, String> error = new AbstractMap.SimpleEntry<>("error", e.getMessage());

        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationException(ConstraintViolationException e, WebRequest request) {
        List<String> validationErrors = e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage())
                .collect(Collectors.toList());

        AbstractMap.SimpleEntry<String, List<String>> error = new AbstractMap.SimpleEntry<>("error", validationErrors);

        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
