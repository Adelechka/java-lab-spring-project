package ru.itis.javalab.dto;

import lombok.Data;

@Data
public class LoginFormDto {
    private String login;
    private String hashPassword;
}
