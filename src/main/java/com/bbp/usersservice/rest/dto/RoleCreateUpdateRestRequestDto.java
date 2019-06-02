package com.bbp.usersservice.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleCreateUpdateRestRequestDto {

    private String name;

    private List<String> permissions;

    private boolean active;
}
