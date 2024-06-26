package org.example.bank.service;

import lombok.AllArgsConstructor;
import org.example.bank.dto.UserDto;
import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.entity.User;
import org.example.bank.repository.UserRepository;
import org.example.bank.service.exception.RegistrationRuntimeException;
import org.example.bank.service.mapper.UserMapper;
import org.example.bank.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;

//read: https://www.baeldung.com/spring-injection-lombok
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public void register(UserRegistrationDto userDto) {
        userValidator.validateUserRegistration(userDto);

        boolean emailIsUsed = userRepository.findByEmail(userDto.getEmail()).isPresent();
        if (emailIsUsed) {
            throw new RegistrationRuntimeException();
        }

// userDto --> user
        User user = userMapper.mapUserRegistrationDtoToUser(userDto);

        userRepository.save(user);
    }

    @Override
    public Page<UserDto> findAll(Integer page, Integer perPage) {
        PageRequest pageRequest = PageRequest.of(page, perPage);

        return userRepository.findAll(pageRequest)
                .map(userMapper::mapUserToUserDto);
//        return userRepository.findAll().stream()
//                .map(userMapper::mapUserToUserDto)
//                .collect(Collectors.toList());
    }

    @Override
    public byte[] loadUsersAsPdf(String filename) {
        // generate pdf
        try {
            return new FileInputStream(ClassLoader.getSystemResource("pdf").getPath() + filename).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
