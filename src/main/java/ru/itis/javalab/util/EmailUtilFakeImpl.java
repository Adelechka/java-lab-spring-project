package ru.itis.javalab.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@Profile("dev")
public class EmailUtilFakeImpl implements EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ExecutorService executorService;

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        System.out.println("mail to " + to + " with subject " + subject + " from " + from + " is successfully sent");
    }
}

