package org.example.bank.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String password;
    private String repeatedPassword;
}
