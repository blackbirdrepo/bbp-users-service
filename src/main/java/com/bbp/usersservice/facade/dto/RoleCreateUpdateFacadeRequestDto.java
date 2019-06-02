package com.bbp.usersservice.facade.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleCreateUpdateFacadeRequestDto {

    private String name;

    private List<String> permissions;

    private boolean active;
}
