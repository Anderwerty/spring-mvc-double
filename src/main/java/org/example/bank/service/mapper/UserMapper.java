package org.example.bank.service.mapper;

import org.example.bank.dto.UserDto;
import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserRegistrationDtoToUser(UserRegistrationDto userDto){
        if(userDto == null){
            return null;
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public UserDto mapUserToUserDto(User user){
        if(user == null){
            return null;
        }
        return UserDto.builder()
                .withId(user.getId())
                .withFirstname(user.getFirstname())
                .withEmail(user.getEmail())
                .build();
    }
}
