package com.shhetri.controller;

import com.shhetri.exceptions.ModelNotFoundException;
import com.shhetri.service.PersonService;
import com.shhetri.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class HomeController {

    private final ProductService productService;
    private final PersonService personService;

    @Autowired
    public HomeController(ProductService productService, PersonService personService) {
        this.productService = productService;
        this.personService = personService;
    }

    @RequestMapping({"/", "/home", "/index"})
    public String home(Model model, Principal principal, HttpServletRequest request) {

        if (principal != null) {
            try {
                model.addAttribute("loggedInUser", personService.findByEmail(principal.getName()));
            } catch (ModelNotFoundException e) {
                new SecurityContextLogoutHandler().logout(request, null, null);
            }
        }

        return "home";
    }
}
