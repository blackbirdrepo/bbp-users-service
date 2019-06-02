package com.bbp.usersservice.rest.dto;

import com.bbp.usersservice.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRestResponseDto {

    private String id;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String gender;

    private String roleId;

    private boolean active;
}
