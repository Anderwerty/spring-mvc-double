package org.example.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@PasswordsMatched.List({
        @PasswordsMatched(password = "password",
                repeatedPassword ="repeatedPassword" )
})
@Builder
public class UserRegistrationDto {

    public UserRegistrationDto(){

    }

    @Email
    private String email;

    @Pattern(regexp = "")
    @Min(value = 5)
    @Max(value = 10)
    private String password;

    @Pattern(regexp = "")
    @Min(value = 5)
    @Max(value = 10)
    private String repeatedPassword;


}
