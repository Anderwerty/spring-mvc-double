package org.example.bank.service.mapper;

import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.entity.User;

public class UserMapper {

    public User mapUserDtoToUser(UserRegistrationDto userDto){
        if(userDto == null){
            return null;
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
