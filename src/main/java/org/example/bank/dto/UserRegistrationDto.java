package org.example.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@PasswordsMatched.List({
//        @PasswordsMatched(password = "password",
//                repeatedPassword ="repeatedPassword" )
//})
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRegistrationDto {

    @Email(message = "msg.email.error.format")
    private String email;

//    @Min(value = 5)
//    @Max(value = 10)
    private String password;

//    @Min(value = 5)
   @Max(value = 10)
    private String repeatedPassword;


}
