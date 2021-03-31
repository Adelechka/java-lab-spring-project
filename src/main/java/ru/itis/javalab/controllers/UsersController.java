package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.javalab.services.UsersService;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users")
    public String getRegistrationPage(Model model) {
        model.addAttribute("users",  usersService.getAllUser());
        return "users_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/banAll")
    public String banUsers() {
        usersService.banAll();
        return "redirect:/users";
    }


}
