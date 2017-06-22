package com.shhetri.controller;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.model.Person;
import com.shhetri.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {
    private final PersonService personService;

    @Autowired
    public ProfileController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/profile")
    public String edit(Model model, Principal principal) throws ModelNotFoundException {
        Person user = personService.findByEmail(principal.getName());
        model.addAttribute("user", user);

        return "user/profile";
    }

    @PostMapping("/profile/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("user") Person person, BindingResult result, RedirectAttributes redirectAttributes) throws ModelNotFoundException {
        if (result.hasErrors()) {
            return "user/profile";
        }

        personService.updatePerson(person, id);
        redirectAttributes.addFlashAttribute("success", "Successfully updated the profile information");
        return "redirect:/profile";
    }
}
