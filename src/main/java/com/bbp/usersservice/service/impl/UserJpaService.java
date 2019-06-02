package com.bbp.usersservice.service.impl;

import com.bbp.usersservice.domain.User;
import com.bbp.usersservice.domain.enums.GenderEnum;
import com.bbp.usersservice.repository.UserRepository;
import com.bbp.usersservice.service.UserService;
import com.bbp.usersservice.service.dto.UserCreateServiceRequestDto;
import com.bbp.usersservice.service.dto.UserUpdateServiceRequestDto;
import com.bbp.usersservice.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserJpaService implements UserService {

    private final UserRepository repository;

    @Override
    public User readUser(String userId) {
        return repository
                .findById(Long.valueOf(userId))
                .orElseThrow(() -> new ObjectNotFoundException(String.format("User with id: %s not found", userId)));
    }

    @Override
    public Page<User> readUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public User createUser(UserCreateServiceRequestDto requestDto) {
        var user = User.create(
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getEmail(),
                requestDto.getPasswordHash(),
                LocalDate.parse(requestDto.getBirthDate()),
                GenderEnum.valueOf(requestDto.getGender().toUpperCase()),
                requestDto.getRole());
        return repository.save(user);
    }

    @Override
    public User updateUser(String userId, UserUpdateServiceRequestDto requestDto) {
        var user = readUser(userId)
                .setFirstName(requestDto.getFirstName())
                .setLastName(requestDto.getLastName())
                .setBirthDate(LocalDate.parse(requestDto.getBirthDate()))
                .setGender(GenderEnum.valueOf(requestDto.getGender().toUpperCase()))
                .setRole(requestDto.getRole())
                .setActive(requestDto.isActive());
        return repository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        repository.delete(readUser(userId));
    }
}
