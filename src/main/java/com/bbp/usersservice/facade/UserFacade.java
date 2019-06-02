package com.bbp.usersservice.facade;

import com.bbp.usersservice.domain.User;
import com.bbp.usersservice.facade.dto.UserCreateFacadeRequestDto;
import com.bbp.usersservice.facade.dto.UserUpdateFacadeRequestDto;
import com.bbp.usersservice.service.RoleService;
import com.bbp.usersservice.service.UserService;
import com.bbp.usersservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;

@RequiredArgsConstructor
@Transactional
@Service
//TODO implement changing password/email with confirmation
public class UserFacade {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public User readUser(String userId) {
        return userService.readUser(userId);
    }

    public Page<User> readUsers(Pageable pageable) {
        return userService.readUsers(pageable);
    }

    //TODO implement email code sending
    public User createUser(UserCreateFacadeRequestDto requestDto) {
        var serviceRequestDto = mapper.toCreateServiceRequest(requestDto);
        serviceRequestDto.setRole(roleService.readRole(requestDto.getRoleId()));
        serviceRequestDto.setPasswordHash(passwordEncoder.encode(CharBuffer.wrap(requestDto.getPassword())));

        return userService.createUser(serviceRequestDto);
    }

    public User updateUser(String userId, UserUpdateFacadeRequestDto requestDto) {
        var serviceRequestDto = mapper.toUpdateServiceRequest(requestDto);
        serviceRequestDto.setRole(roleService.readRole(requestDto.getRoleId()));

        return userService.updateUser(userId, serviceRequestDto);
    }

    public void deleteUser(String userId) {
        userService.deleteUser(userId);
    }
}
