package org.example.bank.service;

import org.example.bank.dto.UserDto;
import org.example.bank.dto.UserRegistrationDto;
import org.springframework.data.domain.Page;

public interface UserService {
    void register(UserRegistrationDto user);

    Page<UserDto> findAll(Integer page, Integer perPage);

    byte[] loadUsersAsPdf(String filename);
}
