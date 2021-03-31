package ru.itis.javalab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
public class LoginController {

    @PermitAll
    @RequestMapping(value = "/login")
    public String getRegistrationPage() {
        return "login_page";
    }
}
