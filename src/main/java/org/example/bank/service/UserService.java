package org.example.bank.service;

import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.entity.User;

public interface UserService {
    void register(UserRegistrationDto user);
}
