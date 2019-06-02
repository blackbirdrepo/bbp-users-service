package com.bbp.usersservice.service.dto;

import com.bbp.usersservice.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateServiceRequestDto {

    private String firstName;

    private String lastName;

    private String birthDate;

    private String gender;

    private boolean active;

    private Role role;
}
