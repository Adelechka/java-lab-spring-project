package ru.itis.javalab.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("/")
public class RootController {

    @PermitAll
    @GetMapping
    public String getRoot(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/profile";
        } else {
            return "redirect:/login";
        }
    }
}
