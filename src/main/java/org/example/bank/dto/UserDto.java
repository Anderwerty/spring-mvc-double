package org.example.bank.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class UserDto {
    private String id;

    private String firstname;

    private String lastname;

    private String email;

}
