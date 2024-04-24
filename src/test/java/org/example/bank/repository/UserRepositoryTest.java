package org.example.bank.repository;


import org.example.bank.config.RepositoryConfig;
import org.example.bank.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringJunit4Runner.class) junit4
// FIRST
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RepositoryConfig.class)
@DirtiesContext
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmailShouldReturnEntity() {
        User user = userRepository.findByEmail("taras.shevchenko@gmail.com").get();

        assertEquals("1", user.getId());
        assertEquals("taras.shevchenko@gmail.com", user.getEmail());
        assertEquals("Taras", user.getFirstname());
        assertEquals("Shevchenko", user.getLastname());
    }

    @Test
    void findByEmailShouldReturnEmptyResult() {
        Optional<User> byEmail = userRepository.findByEmail("unknow@gmail.com");

        assertEquals(true, byEmail.isEmpty());
    }
}