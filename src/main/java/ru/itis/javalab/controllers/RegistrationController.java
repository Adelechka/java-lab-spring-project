package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.dto.RegistrationFormDto;
import ru.itis.javalab.services.SignUpService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.Objects;

@Controller
public class RegistrationController {

    @Autowired
    private SignUpService signUpService;

    @PermitAll
    @RequestMapping(value = "/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("registrationFormDto", new RegistrationFormDto());
        return "registration_page";
    }

    @PermitAll
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid RegistrationFormDto userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("registrationFormDto.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("registrationFormDto", userForm);
            return "registration_page";
        }
        signUpService.signUp(userForm);
        return "redirect:/login";
    }
}
