package com.bbp.usersservice.service.impl;

import com.bbp.usersservice.domain.Role;
import com.bbp.usersservice.domain.enums.GenderEnum;
import com.bbp.usersservice.domain.enums.PermissionEnum;
import com.bbp.usersservice.repository.RoleRepository;
import com.bbp.usersservice.service.RoleService;
import com.bbp.usersservice.service.dto.RoleCreateUpdateServiceRequestDto;
import com.bbp.usersservice.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleJpaService implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role readRole(String roleId) {
        return repository.findById(Long.valueOf(roleId))
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Role with id: %s not found", roleId)));
    }

    @Override
    public List<Role> readRoles() {
        return repository.findAll();
    }

    @Override
    public Role createRole(RoleCreateUpdateServiceRequestDto requestDto) {
        var permissions = requestDto.getPermissions()
                .stream()
                .map(String::toUpperCase)
                .map(PermissionEnum::valueOf)
                .collect(Collectors.toSet());
        var role = Role.create(
                requestDto.getName(),
                permissions);
        return repository.save(role);
    }

    @Override
    public Role updateRole(String roleId, RoleCreateUpdateServiceRequestDto requestDto) {
        var role = readRole(roleId);
        var permissions = requestDto.getPermissions()
                .stream()
                .map(String::toUpperCase)
                .map(PermissionEnum::valueOf)
                .collect(Collectors.toSet());
        role
                .setName(requestDto.getName())
                .setPermissions(permissions)
                .setActive(requestDto.isActive());
        return repository.save(role);
    }

    @Override
    public void deleteRole(String roleId) {
        repository.delete(readRole(roleId));
    }
}
