package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean singIn(User user) {
        User auth = usersRepository.findByLogin(user.getLogin()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return passwordEncoder.matches(user.getHashPassword(), auth.getHashPassword());
    }
}
