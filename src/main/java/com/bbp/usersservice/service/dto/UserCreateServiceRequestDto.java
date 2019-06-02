package com.bbp.usersservice.service.dto;

import com.bbp.usersservice.domain.Role;
import com.bbp.usersservice.domain.enums.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateServiceRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String passwordHash;

    private String birthDate;

    private String gender;

    private Role role;
}
