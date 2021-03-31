package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

public interface SignInService {
    boolean singIn(User user);
}
