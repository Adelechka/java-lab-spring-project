package ru.itis.javalab.services;

import ru.itis.javalab.dto.RegistrationFormDto;
import ru.itis.javalab.models.User;

public interface SignUpService {
    void signUp(RegistrationFormDto userForm);
}
