package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;

import java.util.List;

public interface UsersService {
    User findByConfirmCode(String confirmCode);
    void updateState(Long id);
    void saveUser(User user);
    List<UserDto> getAllUsers();
    List<User> getAllUser();
    void banAll();
}
