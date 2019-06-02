package com.bbp.usersservice.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleCreateUpdateServiceRequestDto {

    private String name;

    private List<String> permissions;

    private boolean active;
}
