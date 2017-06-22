package com.shhetri.controller;

import com.shhetri.model.Person;
import com.shhetri.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RegistrationController {
    private final PersonService personService;

    @Autowired
    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/register")
    public String registerView(Model model, Principal principal) {
        if (principal != null && ((Authentication) principal).isAuthenticated()) {
            return "redirect:/home";
        }

        Person user = new Person();
        model.addAttribute("user", user);

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Person person, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        personService.savePerson(person);
        redirectAttributes.addFlashAttribute("success", "Successfully registered. Please login with your credentials");
        return "redirect:/login";
    }
}
