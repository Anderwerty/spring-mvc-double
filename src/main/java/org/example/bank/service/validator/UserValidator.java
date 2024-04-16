package org.example.bank.service.validator;

import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.service.exception.RegistrationRuntimeException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validateUserRegistration(UserRegistrationDto user) {
        if (user == null || !user.getPassword().equals(user.getRepeatedPassword())) {
            throw new RegistrationRuntimeException();
        }
        // password pattern
        // email pattern
    }
}
