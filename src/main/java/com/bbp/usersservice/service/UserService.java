package com.bbp.usersservice.service;


import com.bbp.usersservice.domain.User;
import com.bbp.usersservice.service.dto.UserCreateServiceRequestDto;
import com.bbp.usersservice.service.dto.UserUpdateServiceRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User readUser(String id);

    Page<User> readUsers(Pageable pageable);

    User createUser(UserCreateServiceRequestDto requestDto);

    User updateUser(String userId, UserUpdateServiceRequestDto requestDto);

    void deleteUser(String id);
}
