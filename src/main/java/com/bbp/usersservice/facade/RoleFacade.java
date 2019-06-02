package com.bbp.usersservice.facade;

import com.bbp.usersservice.domain.Role;
import com.bbp.usersservice.facade.dto.RoleCreateUpdateFacadeRequestDto;
import com.bbp.usersservice.service.RoleService;
import com.bbp.usersservice.service.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RoleFacade {

    private final RoleService roleService;
    private final RoleMapper mapper;

    public Role readRole(String roleId) {
        return roleService.readRole(roleId);
    }

    public List<Role> readRoles() {
        return roleService.readRoles();
    }

    public Role createRole(RoleCreateUpdateFacadeRequestDto requestDto) {
        var serviceRequestDto = mapper.toCreateUpdateServiceRequest(requestDto);
        return roleService.createRole(serviceRequestDto);
    }

    public Role updateRole(String roleId, RoleCreateUpdateFacadeRequestDto requestDto) {
        var serviceRequestDto = mapper.toCreateUpdateServiceRequest(requestDto);
        return roleService.updateRole(roleId, serviceRequestDto);
    }

    public void deleteRole(String roleId) {
        roleService.deleteRole(roleId);
    }
}
