package com.bbp.usersservice.facade.dto;

import com.bbp.usersservice.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateFacadeRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private char[] password;

    private char[] passwordConfirmation;

    private String birthDate;

    private String gender;

    private String roleId;

    @AssertTrue(message = "Password should be identical with confirmation")
    public boolean isPasswordsIdentical() {
        return Arrays.equals(password, passwordConfirmation);
    }
}
