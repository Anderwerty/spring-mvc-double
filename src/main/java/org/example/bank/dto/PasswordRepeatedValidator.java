package org.example.bank.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordRepeatedValidator implements ConstraintValidator<PasswordsMatched, Object> {

    private String password;
    private String repeatedPassword;

    private String message;

    @Override
    public void initialize(PasswordsMatched constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.repeatedPassword = constraintAnnotation.repeatedPassword();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //...
        return false;
    }
}
