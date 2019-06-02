package com.bbp.usersservice.service;

import com.bbp.usersservice.domain.Role;
import com.bbp.usersservice.service.dto.RoleCreateUpdateServiceRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    Role readRole(String id);

    List<Role> readRoles();

    Role createRole(RoleCreateUpdateServiceRequestDto requestDto);

    Role updateRole(String roleId, RoleCreateUpdateServiceRequestDto requestDto);

    void deleteRole(String id);
}
