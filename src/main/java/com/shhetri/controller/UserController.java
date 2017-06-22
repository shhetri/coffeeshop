package com.shhetri.controller;

import com.shhetri.model.Authority;
import com.shhetri.model.Person;
import com.shhetri.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    private final PersonService personService;

    @Autowired
    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String index(Model model) {
        return "admin/user/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Person user = new Person();
        model.addAttribute("user", user);

        return "admin/user/create";
    }

    @PostMapping("")
    public String store(@Valid @ModelAttribute("user") Person person, BindingResult bindingResult, String role, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/user/create";
        }

        Authority authority = new Authority(person, role);
        person.setAuthorities(Collections.singletonList(authority));

        personService.savePerson(person);

        redirectAttributes.addFlashAttribute("success", "User successfully created");

        return "redirect:/admin/users";
    }
}
