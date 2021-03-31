package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.RegistrationFormDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailsGenerator;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.theme}")
    private String mailTheme;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void signUp(RegistrationFormDto userForm) {
        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .age(userForm.getAge())
                .login(userForm.getLogin())
                .email(userForm.getEmail())
                .confirmCode(UUID.randomUUID().toString())
                .hashPassword(passwordEncoder.encode(userForm.getHashPassword()))
                .status(User.Status.NOT_CONFIRMED)
                .state(User.State.ACTIVE)
                .role(User.Role.USER)
                .build();

        usersRepository.save(user);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), mailTheme, from, confirmMail);
    }
}
