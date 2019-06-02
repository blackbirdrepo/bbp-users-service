package com.bbp.usersservice.facade.dto;

import com.bbp.usersservice.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateFacadeRequestDto {

    private String firstName;

    private String lastName;

    private String birthDate;

    private String gender;

    private String roleId;

    private boolean active;
}
