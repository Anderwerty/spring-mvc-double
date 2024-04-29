package org.example.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bank.dto.UserRegistrationDto;
import org.example.bank.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@RunWith(MockitoJunitRunner.class)
//@RunWith(SpringJunitRunner.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes = {RepositoryConfig.class, ServiceConfig.class})
@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles({"test"})
class UserControllerTest {
    @Autowired
    UserService userService;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserController(userService))
                .build();
    }

    @Test
    void getRegistrationForm() throws Exception {
        Mockito.when(userService.findAll(1, 20))
                .thenThrow(RuntimeException.class)
                .thenReturn(null);
//        Mockito.doReturn().when(userService).findAll(1,20)
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verifyNoInteractions(userService);
    }

    @Test
    void postRegistrationData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserRegistrationDto user = UserRegistrationDto.builder()
                .email("example@gmail.com")
                .password("password")
                .repeatedPassword("password").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                        .content(mapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

@Configuration
class TestConfig {

    // TODO: spy
    // @Spy, Mockito.spy()
    //
    @Bean
    public UserService userService() {

        UserService mock = Mockito.mock(UserService.class);
//        Mockito.when(mock.findAll(1,10)).thenReturn()
        return mock;
    }
}