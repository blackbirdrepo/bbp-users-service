package com.bbp.usersservice.service.mapper;

import com.bbp.usersservice.domain.Role;
import com.bbp.usersservice.facade.dto.RoleCreateUpdateFacadeRequestDto;
import com.bbp.usersservice.rest.dto.RoleCreateUpdateRestRequestDto;
import com.bbp.usersservice.rest.dto.RoleRestResponseDto;
import com.bbp.usersservice.service.dto.RoleCreateUpdateServiceRequestDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleCreateUpdateFacadeRequestDto toCreateUpdateFacadeRequest(RoleCreateUpdateRestRequestDto requestDto);

    RoleCreateUpdateServiceRequestDto toCreateUpdateServiceRequest(RoleCreateUpdateFacadeRequestDto requestDto);

    RoleRestResponseDto toRestResponse(Role role);

    default List<RoleRestResponseDto> toRestResponseList(List<Role> roles) {
        return roles.stream()
                .map(this::toRestResponse)
                .collect(Collectors.toList());
    }
}
