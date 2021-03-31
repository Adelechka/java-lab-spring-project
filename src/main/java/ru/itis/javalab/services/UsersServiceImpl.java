package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

@Service

public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User findByConfirmCode(String confirmCode) {
        return usersRepository.findByConfirmCode(confirmCode);
    }

    @Override
    public void updateState(Long id) {
        usersRepository.updateStatus(id, User.Status.CONFIRMED);
    }

    @Override
    public void saveUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public List<User> getAllUser() {
        return usersRepository.findAll();
    }

    @Override
    public void banAll() {
        List<User> users = usersRepository.findAll();
        for (User user : users) {
            if (!user.isAdmin()) {
                user.setState(User.State.BANNED);
                usersRepository.save(user);
            }
        }
    }

}
