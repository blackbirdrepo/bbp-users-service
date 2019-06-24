package com.bbp.usersservice.facade;

import com.bbp.usersservice.domain.User;
import com.bbp.usersservice.facade.dto.UserCreateFacadeRequestDto;
import com.bbp.usersservice.facade.dto.UserUpdateFacadeRequestDto;
import com.bbp.usersservice.service.RoleService;
import com.bbp.usersservice.service.UserService;
import com.bbp.usersservice.service.dto.EmailDto;
import com.bbp.usersservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.nio.CharBuffer;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Service
//TODO implement changing password/email with confirmation
public class UserFacade {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final KafkaTemplate<String, EmailDto> kafkaTemplate;

    @Value("${kafka.topics.email}")
    private String emailTopicName;


    public User readUser(String userId) {
        return userService.readUser(userId);
    }

    public Page<User> readUsers(Pageable pageable) {
        return userService.readUsers(pageable);
    }

    //TODO implement activation code sending via email
    public User createUser(UserCreateFacadeRequestDto requestDto) {
        var serviceRequestDto = mapper.toCreateServiceRequest(requestDto);
        serviceRequestDto.setRole(roleService.readRole(requestDto.getRoleId()));
        serviceRequestDto.setPasswordHash(passwordEncoder.encode(CharBuffer.wrap(requestDto.getPassword())));

        var createdUser = userService.createUser(serviceRequestDto);
        var emailDto = new EmailDto()
                .setEmailAddress(createdUser.getEmail())
                .setEmailType("activation")
                //TODO replace with activation code
                .setContentProperties(Map.of("name", createdUser.getFirstName(), "url", "http://google.com"));
        kafkaTemplate.send(emailTopicName, emailDto);
        return createdUser;
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
