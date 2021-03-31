package ru.itis.javalab.dto;

import lombok.Data;
import ru.itis.javalab.validation.ValidNames;
import ru.itis.javalab.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ValidNames(
        message = "{errors.incorrect.names}",
        name = "firstName",
        surname = "lastName"
)
public class RegistrationFormDto {
    @NotBlank(message = "{errors.incorrect.notBlank}")
    private String firstName;

    @NotBlank(message = "{errors.incorrect.notBlank}")
    private String lastName;

    private Integer age;

    @NotBlank(message = "{errors.incorrect.notBlank}")
    private String login;

    @NotBlank(message = "{errors.incorrect.notBlank}")
    @Email(message = "{errors.incorrect.email}")
    private String email;

    @NotBlank(message = "{errors.incorrect.notBlank}")
    @ValidPassword(message = "{errors.incorrect.password}")
    private String hashPassword;
}
