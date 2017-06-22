package com.shhetri.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(Principal principal, Model model) {
        if (principal != null && ((Authentication) principal).isAuthenticated()) {
            return "redirect:/home";
        }

        return "auth/login";
    }
}
