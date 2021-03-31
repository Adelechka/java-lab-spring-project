package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.annotation.security.PermitAll;

@Controller
public class ConfirmController {

    @Autowired
    private UsersService usersService;

    @PermitAll
    @RequestMapping(value = "/confirm/{confirm_code}")
    public String getRegistrationPage(@PathVariable String confirm_code, Model model) {
        if (usersService.findByConfirmCode(confirm_code) != null) {
            User user = usersService.findByConfirmCode(confirm_code);
            System.out.println("login: " + user.getLogin() + " confirm code: " + user.getConfirmCode());
            usersService.updateState(user.getId());
            return "redirect:/profile";
        } else {
            return "redirect:/registration";
        }
    }
}
