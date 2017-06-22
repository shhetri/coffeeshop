package com.shhetri.controller;

import com.shhetri.model.Person;
import com.shhetri.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final PersonService personService;

    @Autowired
    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/register")
    public String registerView(Model model) {
        Person user = new Person();
        model.addAttribute("user", user);

        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        personService.savePerson(person);

        return "redirect:/login";
    }
}
