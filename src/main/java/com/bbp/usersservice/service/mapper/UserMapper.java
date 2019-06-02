package com.bbp.usersservice.service.mapper;

import com.bbp.usersservice.domain.User;
import com.bbp.usersservice.facade.dto.UserCreateFacadeRequestDto;
import com.bbp.usersservice.facade.dto.UserUpdateFacadeRequestDto;
import com.bbp.usersservice.rest.dto.UserCreateRestRequestDto;
import com.bbp.usersservice.rest.dto.UserRestResponseDto;
import com.bbp.usersservice.rest.dto.UserUpdateRestRequestDto;
import com.bbp.usersservice.service.dto.UserCreateServiceRequestDto;
import com.bbp.usersservice.service.dto.UserUpdateServiceRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCreateFacadeRequestDto toCreateFacadeRequest(UserCreateRestRequestDto requestDto);
    UserUpdateFacadeRequestDto toUpdateFacadeRequest(UserUpdateRestRequestDto requestDto);

    UserCreateServiceRequestDto toCreateServiceRequest(UserCreateFacadeRequestDto requestDto);

    UserUpdateServiceRequestDto toUpdateServiceRequest(UserUpdateFacadeRequestDto requestDto);

    @Mapping(target = "roleId", source = "user.role.id")
    @Mapping(target = "gender", expression = "java(user.getGender().name().toLowerCase())")
    UserRestResponseDto toRestResponse(User user);

    default Page<UserRestResponseDto> toRestResponsePage(Page<User> userPage) {
        return userPage.map(this::toRestResponse);
    }
}
