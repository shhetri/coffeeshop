package com.shhetri.exceptions.handlers;

import com.shhetri.exceptions.ModelNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ModelNotFoundException.class)
    public String handleNotFound(ModelNotFoundException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());

        return "redirect:/home";
    }
}
