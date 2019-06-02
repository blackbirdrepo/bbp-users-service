package com.bbp.usersservice.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRestRequestDto {

    private String firstName;

    private String lastName;

    private String birthDate;

    private String gender;

    private String roleId;

    private boolean active;
}
