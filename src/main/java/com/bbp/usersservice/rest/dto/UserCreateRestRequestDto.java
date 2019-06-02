package com.bbp.usersservice.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRestRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private char[] password;

    private char[] passwordConfirmation;

    private String birthDate;

    private String gender;

    private String roleId;
}
